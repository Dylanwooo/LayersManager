<%@page session="false"%>
<%@page import="java.net.*, java.io.*"%>
<%!String[] serverUrls = {
	//"<url>[,<token>]"
		"http://service.arcgisonline.cn/search/0d662add2442520171ce26ef12954f03952b942d5ef78a0c416b53c53bb118f3/Route"
};
%>
<%
	try {
		String reqUrl = request.getQueryString();
		URL url = new URL(reqUrl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setDoOutput(true);
		con.setReadTimeout(600000);
		con.setRequestMethod(request.getMethod());
		if (request.getContentType() != null) {
			con.setRequestProperty("Content-Type", request.getContentType());
		}
		con.setRequestProperty("Referer", request.getHeader("Referer"));
		int clength = request.getContentLength();
		if (clength > 0) {
			con.setDoInput(true);
			InputStream istream = request.getInputStream();
			OutputStream os = con.getOutputStream();
			final int length = 5000;
			byte[] bytes = new byte[length];
			int bytesRead = 0;
			while ((bytesRead = istream.read(bytes, 0, length)) > 0) {
				os.write(bytes, 0, bytesRead);
			}
		} else {
			con.setRequestMethod("GET");
		}
		out.clear();
		out = pageContext.pushBody();
		OutputStream ostream = response.getOutputStream();
		response.setContentType(con.getContentType());
		InputStream in = con.getInputStream();
		final int length = 5000;
		byte[] bytes = new byte[length];
		int bytesRead = 0;
		while ((bytesRead = in.read(bytes, 0, length)) > 0) {
			ostream.write(bytes, 0, bytesRead);
		}
	} catch (Exception e) {
		response.setStatus(500);
	}
%>