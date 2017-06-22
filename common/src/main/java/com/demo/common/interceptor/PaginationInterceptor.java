package com.demo.common.interceptor;

import com.demo.common.PageModel;
import com.demo.common.PageQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by chenww3 on 2015/4/17.
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )})
public class PaginationInterceptor implements Interceptor {

    private final static Log log = LogFactory.getLog(PaginationInterceptor.class);

    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    //分页的id后缀
    private static final String SUFFIX_PAGE = "_PageModel";
    //count查询的id后缀
    private static final String SUFFIX_COUNT = SUFFIX_PAGE + "_Count";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //当前环境 MappedStatement，BoundSql，及sql取得
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String originalSql = boundSql.getSql().trim();
        log.debug("originalSql=" + originalSql);
        Object parameterObject = boundSql.getParameterObject();

        PageQuery pageQuery = getPageQuery(parameterObject);
//        PageQuery pageQuery = searchPageWithXpath(parameterObject, ".", "param1");
        log.debug("pageQuery=" + pageQuery);
        if (pageQuery == null) {
            return invocation.proceed();
        }

        //pageSizeZero的判断
        if ((pageQuery.getPageSizeZero() != null && pageQuery.getPageSizeZero()) && pageQuery.getPageSize() == 0) {
            //执行正常（不分页）查询
            Object result = invocation.proceed();
            return ceateNoPagination(pageQuery, result);
        }


        int totalCount = selectTotalCont(invocation, mappedStatement, parameterObject, pageQuery);
        pageQuery.setTotalCount(totalCount);
        if (pageQuery.getTotalCount() == 0) {
            return new PageModel(pageQuery, new ArrayList(0));
        }

        List datas = selectDatas(invocation, mappedStatement, parameterObject, pageQuery);

        PageModel pageModel = new PageModel(pageQuery, datas);
        log.debug("------2---------pageModel=" + pageModel);

//        invocation.getArgs()[0] = mappedStatement;
        return pageModel;
    }

    private List selectDatas(Invocation invocation, MappedStatement mappedStatement, Object parameterObject, PageQuery pageQuery) throws Throwable {
        SqlSource pageSqlSource = getPageSqlSource(mappedStatement, parameterObject, pageQuery);
        MappedStatement pageMs = newMappedStatement(mappedStatement, pageSqlSource, SUFFIX_PAGE);
        invocation.getArgs()[0] = pageMs;
        Object result = invocation.proceed();
        log.debug("---------------result2=" + result);
        List datas = (List) result;
        log.debug("---------------datas=" + datas);
        return  datas;
    }

    private int selectTotalCont(Invocation invocation, MappedStatement mappedStatement, Object parameterObject, PageQuery pageQuery) throws Throwable {
        SqlSource countSqlSource = getCountSqlSource(mappedStatement, parameterObject, pageQuery);
        MappedStatement countMs = newMappedStatement(mappedStatement, countSqlSource, SUFFIX_COUNT);
        invocation.getArgs()[0] = countMs;
        Object result = invocation.proceed();
        log.debug("---------------result1=" + result);
        int totalCount = (Integer) ((List) result).get(0);
        log.debug("---------------totalCount=" + totalCount);
        return totalCount;
    }


    private PageModel ceateNoPagination(PageQuery pageQuery, Object result) {
        //得到处理结果
        List datas = (List) result;
        //相当于查询第一页
        pageQuery.setPageNum(1);
        //这种情况相当于pageSize=total
        pageQuery.setPageSize(datas.size());
        //仍然要设置total
        pageQuery.setTotalCount(datas.size());

        PageModel pageModel = new PageModel(pageQuery, datas);
        if (log.isDebugEnabled()) {
            log.debug("pageSizeZero result pageModel=" + pageModel);
        }
        return pageModel;
    }

    private PageQuery getPageQuery(Object parameterObject) {
        log.debug("parameterObject=" + parameterObject);
        if (parameterObject instanceof PageQuery) {    //参数就是Page实体
            return (PageQuery) parameterObject;
        } else if (parameterObject instanceof Map) {    //参数就是Page实体
            Map query = (Map) parameterObject;
            log.debug("---------------query=" + query);
            for (Object valueObject : query.values()) {
                if (valueObject instanceof PageQuery) {
                    return (PageQuery) valueObject;
                }
            }
        }
        return null;
//        else {  //参数为某个实体，该实体拥有Page属性
//            Field pageField = ReflectHelper.getFieldByFieldName(parameterObject, "PageQuery");
//            log.debug("---------------pageField=" + pageField);
//            if (pageField != null) {
//                pageQuery = (PageQuery) ReflectHelper.getValueByFieldName(parameterObject, "pageQuery");
//                if (pageQuery == null)
//                    pageQuery = new PageQuery();
//                ReflectHelper.setValueByFieldName(parameterObject, "pageQuery", pageQuery); //通过反射，对实体对象设置分页对象
//            } else {
//                throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
//            }
//        }
    }

//    /**
//     * 根据给定的xpath查询Page对象
//     */
//    private PageQuery searchPageWithXpath(Object o, String... xpaths) {
//        log.debug("-----------Object=" + o);
//        JXPathContext context = JXPathContext.newContext(o);
//
//        Object result;
//        for (String xpath : xpaths) {
//            log.debug("--------------xpath----=" + xpath);
//            try {
//                result = context.selectSingleNode(xpath);
//            } catch (JXPathNotFoundException e) {
//                continue;
//            }
//            if (result instanceof PageQuery) {
//                return (PageQuery) result;
//            }
//        }
//        return null;
//    }

    /**
     * 复制BoundSql对象
     */
    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    private SqlSource getCountSqlSource(MappedStatement ms, Object parameterObject, PageQuery pageQuery) {
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        String originalSql = boundSql.getSql().trim();

        String countSql = getCountSql(originalSql);
        log.debug("countSql=" + countSql);

        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, countSql);
        return new BoundSqlSqlSource(newBoundSql);
    }

    private SqlSource getPageSqlSource(MappedStatement ms, Object parameterObject, PageQuery pageQuery) {
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        String originalSql = boundSql.getSql().trim();

        String dataSql = originalSql + " limit " + pageQuery.getStartRow() + " , " + pageQuery.getPageSize();
        log.debug("dataSql=" + dataSql);
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, dataSql);
        return new BoundSqlSqlSource(newBoundSql);
    }

    /**
     * 新建count查询和分页查询的MappedStatement
     *
     * @param ms
     * @param sqlSource
     * @param suffix
     * @return
     */
    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource sqlSource, String suffix) {
        String id = ms.getId() + suffix;
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), id, sqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        if (suffix == SUFFIX_PAGE) {
            builder.resultMaps(ms.getResultMaps());
            List<ResultMap> resultMaps = ms.getResultMaps();
        } else {
            //count查询返回值int
            List<ResultMap> resultMaps = new ArrayList<ResultMap>();
            ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), id, int.class, EMPTY_RESULTMAPPING).build();
            resultMaps.add(resultMap);
            builder.resultMaps(resultMaps);
        }
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }


    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     */
    private String getCountSql(String sql) {
        return "SELECT COUNT(*) FROM (" + sql + ") aliasForPageModelCount";
    }

    public class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //数据库方言
        String dialect = properties.getProperty("dialect");
        log.debug("-----------dialect=" + dialect);
//        sqlUtil = new SqlUtil(dialect);
//        sqlUtil.setProperties(p);
    }
}

