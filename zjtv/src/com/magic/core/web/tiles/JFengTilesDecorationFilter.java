package com.magic.core.web.tiles;

import org.apache.commons.lang.StringUtils;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.reflect.CannotInstantiateObjectException;
import org.apache.tiles.request.reflect.ClassUtil;
import org.apache.tiles.request.servlet.ServletUtil;
import org.apache.tiles.web.util.AttributeContextMutator;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Yin Jian Feng
 * Date: 13-3-30
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */

public class JFengTilesDecorationFilter implements Filter{

    /**
     * Init parameter to define the key of the container to use.
     *
     * @since 2.1.2
     */
    public static final String CONTAINER_KEY_INIT_PARAMETER =
            "org.apache.tiles.web.util.TilesDecorationFilter.CONTAINER_KEY";

    /**
     * The logging object.
     */
    private org.slf4j.Logger log = LoggerFactory.getLogger(JFengTilesDecorationFilter.class);

    /**
     * Filter configuration.
     */
    private FilterConfig filterConfig;

    /**
     * The key under which the container is stored.
     */
    private String containerKey;

    /**
     * The name of the definition attribute used to
     * pass on the request.
     */
    private String definitionAttributeName = "content";

    /**
     * The definition name to use.
     */
    private String definitionName = "layout";

    /**
     * Token used to prevent re-decoration of requests.
     * This token is used to prevent infinate loops on
     * filters configured to match wildcards.
     */
    private String preventDecorationToken;

    /**
     * Stores a map of the type "mask -> definition": when a definition name
     * mask is identified, it is substituted with the configured definition.
     */
    private Map<String, String> alternateDefinitions;

    /**
     * The object that will mutate the attribute context so that it uses
     * different attributes.
     */
    private AttributeContextMutator mutator = null;

    /**
     * The servlet context.
     */
    private ServletContext servletContext;

    /** {@inheritDoc} */
    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
        servletContext = filterConfig.getServletContext();

        containerKey = filterConfig
                .getInitParameter(CONTAINER_KEY_INIT_PARAMETER);

        String temp = config.getInitParameter("attribute-name");
        if (temp != null) {
            definitionAttributeName = temp;
        }

        temp = config.getInitParameter("definition");
        if (temp != null) {
            definitionName = temp;
        }

        temp = config.getInitParameter("prevent-token");
        preventDecorationToken = "org.apache.tiles.decoration.PREVENT:"
                + (temp == null ? definitionName : temp);

        alternateDefinitions = parseAlternateDefinitions();

        temp = config.getInitParameter("mutator");
        if (temp != null) {
            try {
                mutator = (AttributeContextMutator) ClassUtil.instantiate(temp);
            } catch (CannotInstantiateObjectException e) {
                throw new ServletException("Unable to instantiate specified context mutator.", e);
            }
        } else {
            mutator = new DefaultMutator();
        }
    }

    /**
     * Creates the alternate definitions map, to map a mask of definition names
     * to a configured definition.
     *
     * @return The alternate definitions map.
     */
    @SuppressWarnings("unchecked")
    protected Map<String, String> parseAlternateDefinitions() {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> e = filterConfig.getInitParameterNames();
        while (e.hasMoreElements()) {
            String parm = e.nextElement();
            if (parm.startsWith("definition(") && parm.endsWith("*)")) {
                String value = filterConfig.getInitParameter(parm);
                String mask = parm.substring("definition(".length());
                mask = mask.substring(0, mask.lastIndexOf("*)"));
                map.put(mask, value);
                log.info("Mapping all requests matching '" + mask
                        + "*' to definition '" + value + "'");
            }
        }
        return map;
    }

    /** {@inheritDoc} */
    public void destroy() {
        filterConfig = null;
    }


    /**
     * {@inheritDoc}
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
//        res.setCharacterEncoding("UTF-8");
//        res.setContentType("text/html;charset=UTF-8");
        // If the request contains the prevent token, we must not reapply the definition.
        // This is used to ensure that filters mapped to wild cards do not infinately
        // loop.
        try{
            if (!isPreventTokenPresent(req)) {
//                JspHandler.getJspContent(req, res, getRequestBase(req));

                ApplicationContext applicationContext = ServletUtil
                        .getApplicationContext(servletContext);
                Request request = new org.apache.tiles.request.servlet.ServletRequest(applicationContext,
                        (HttpServletRequest) req, (HttpServletResponse) res);
                TilesContainer container = TilesAccess.getContainer(applicationContext,
                        containerKey);
                mutator.mutate(container.getAttributeContext(request), req);
                if (preventDecorationToken != null) {
                    req.setAttribute(preventDecorationToken, Boolean.TRUE);
                }
                String definitionName = getDefinitionForRequest(req);
                container.render(definitionName, request);
            }
            filterChain.doFilter(req, res);
        }catch (Exception e){
//            e.printStackTrace();
            log.error(e.getMessage());
//            HandlerForward.exception((HttpServletRequest)req,(HttpServletResponse)res,e);
        }
    }

    /**
     * Method added by YinJianFeng
     * Get tiles template name from request with key "tiles_layout_name". if not found, default definitionName will be used.
     *
     */
    protected String getDefinitionForRequest(ServletRequest request){
        String layoutName = (String) request.getAttribute("tiles_layout_name");
        if (StringUtils.isNotEmpty(layoutName))
            return layoutName;
        return definitionName;
    }
    /**
     * Returns the final definition to render for the given request.
     *
     * @param request The request object.
     * @return The final definition name.
     */
   /*
    commented by YinJianFeng
   private String getDefinitionForRequest(javax.servlet.ServletRequest request) {
        if (alternateDefinitions.size() < 1) {
            return definitionName;
        }
        String base = getRequestBase(request);
        for (Map.Entry<String, String> pair : alternateDefinitions.entrySet()) {
            if (base.startsWith(pair.getKey())) {
                return pair.getValue();
            }
        }
        return definitionName;
    }*/

    /**
     * Returns the request base, i.e. the the URL to calculate all the relative
     * paths.
     *
     * @param request The request object to use.
     * @return The request base.
     */
    private String getRequestBase(ServletRequest request) {
        // Included Path
        String include = (String) request.getAttribute("javax.servlet.include.servlet_path");
        if (include != null) {
            return include;
        }

        // As opposed to includes, if a forward occurs, it will update the servletPath property
        // and include the original as the request attribute.
        return ((HttpServletRequest) request).getServletPath();
    }

    /**
     * The default attribute context mutator to use.
     */
    class DefaultMutator implements AttributeContextMutator {

        /** {@inheritDoc} */
        public void mutate(AttributeContext ctx, ServletRequest req) {
            Attribute attr = new Attribute();
            attr.setRenderer("template");
            attr.setValue(getRequestBase(req));
            ctx.putAttribute(definitionAttributeName, attr);
        }
    }

    /**
     * Checks if the prevent evaluation token is present.
     *
     * @param request The HTTP request object.
     * @return <code>true</code> if the token is present.
     */
    private boolean isPreventTokenPresent(ServletRequest request) {
        return preventDecorationToken != null && request.getAttribute(preventDecorationToken) != null;
    }
}
