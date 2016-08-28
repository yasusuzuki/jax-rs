package jaxrs_test;
 
 
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * このクラスがないと、JAX-RSのサーバ側からのHTTPレスポンスヘッダに以下の行が設定されない。
 *    Access-Control-Allow-Origin: *
 * ローカルでJavaScriptを実行し、JAX-RSのリクエストだけサーバを呼ぶと、Webのルールに従い、
 * 上の設定がHTTPレスポンスヘッダにないと、ブラウザが拒否してしまう。
 * リクエストした側がなぜ拒否するのか不自然で、クライアント(JavaScript)側の設定で対応
 * するほうがいいのでは？と思うがJavaScriptでは対応策がないらしい。
 */
@WebFilter(filterName = "Cors",urlPatterns = {"/*"} ) 
public class ServletFilterCors implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */ 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        res.setHeader("Access-Control-Max-Age", "-1");
        System.out.println("dofilter");
		chain.doFilter(request, response);
	} 

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
