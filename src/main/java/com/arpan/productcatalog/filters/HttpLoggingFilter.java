package com.arpan.productcatalog.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HttpLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // TODO Generate RequestID
        UUID requestId = UUID.randomUUID();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Map<String, String> requestMap = this.getTypesafeRequestMap(httpServletRequest);
//        BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(httpServletRequest);
//        BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper(httpServletResponse);
//        bufferedResponse.setHeader("requestId", requestId.toString());

        try {
            log.info("Logging Request info >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            final StringBuilder reqLogMessage = new StringBuilder(
                    ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REST Request Start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                    .append("\n")
                    .append("@REQUEST ID: ").append(requestId)
                    .append("\n")
                    .append("@HTTP METHOD: ").append(httpServletRequest.getMethod())
                    .append("\n")
                    .append("@PATH INFO: ").append(httpServletRequest.getServletPath())
                    .append("\n")
                    .append("@REQUEST HEADERS: ").append(composeMapLog(getRequestHeadersInfo(httpServletRequest)))
                    .append("\n")
                    .append("@REQUEST PARAMETERS: ").append(requestMap)
                    .append("\n")
                    //.append("@REQUEST BODY: ").append(nulll == bufferedRequest.getRequestBody() ? "" : bufferedRequest.getRequestBody())
                    .append("\n")
                    .append("@REMOTE ADDRESS: ").append(httpServletRequest.getRemoteAddr())
                    .append("\n")
                    .append("@HTTP METHOD: ").append(httpServletRequest.getMethod())
                    .append("\n")
                    .append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< REST Request End <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.info("\n" + reqLogMessage.toString());
        } catch (Throwable t) {
            log.error("Exception logging 'request' in HttpLoggingFilter, it can be disabled by removing the 'httpLoggingEnable' spring profile", t);
        }
        try {

        } catch (Exception ex) {
            log.info("Exception occurred for requestId >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + requestId);
            log.error("Exception occurred for requestId" + requestId, ex);
        } finally {
            log.info("Logging Response info >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
            try {
                final StringBuilder respLogMessage = new StringBuilder(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REST Response Start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                        .append("\n")
                        .append("@REQUEST ID: ").append(requestId)
                        .append("\n")
                        //.append("@RESPONSE STATUS: ").append(bufferedResponse.getStatus())
                        .append("\n")
                        //.append("@RESPONSE HEADERS: ").append(composeMapLog(getResponseHeaderInfo(bufferedResponse)))
                        .append("\n")
                        //.append("@RESPONSE CONTENT TYPE: ").append(bufferedResponse.getContentType())
                        .append("\n")
                        //.append("@RESPONSE CONTENT: ").append(null == bufferedResponse.getContent() ? "" : bufferedResponse.getContent())
                        .append("\n")
                        .append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REST Response End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            } catch (Throwable t) {
                log.error("Exception logging response in HttpLoggingFilter, it can be disabled by removing the 'httpLoggingEnable' spring profile", t);
            }
        }

        filterChain.doFilter(request, response);
    }

    private Map<String, String> getTypesafeRequestMap(HttpServletRequest request) {
        Map<String, String> typeSafeRequestMap = new HashMap<>();
        Enumeration<?> requestParams = request.getParameterNames();
        while (requestParams.hasMoreElements()) {
            String requestParamName = (String) requestParams.nextElement();
            String requestParamValue;
            if (requestParamName.equalsIgnoreCase("password")) {
                requestParamValue = "********";
            } else {
                requestParamValue = request.getParameter(requestParamName);
            }
            typeSafeRequestMap.put(requestParamName, requestParamValue);
        }
        return typeSafeRequestMap;
    }

    private Map<String, String> getRequestHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private StringBuilder composeMapLog(Map<String, String> map) {
        StringBuilder str = new StringBuilder();
        map.forEach((k, v) -> {
            str.append("\t");
            str.append(k);
            str.append(" : ");
            str.append(v);
            str.append("\n");
        });
        return str;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /*
    private static final class BufferedRequestWrapper extends HttpServletRequestWrapper {
        private byte[] buffer = null;

        public BufferedRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            // Read InputStream and store its content in a buffer
            ByteArrayOutputStream baos = null;
            InputStream is = request.getInputStream();

            try {
                baos = new ByteArrayOutputStream();
                byte buf[] = new byte[1024];
                int read;
                while ((read = is.read(buf)) > 0) {
                    baos.write(buf, 0, read);
                }
                this.buffer = baos.toByteArray();
            } finally {
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new BufferedServletInputStream(new ByteArrayInputStream(this.buffer));
        }

        String getRequestBody() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
            String line = null;
            StringBuilder inputBuffer = new StringBuilder();

            try {
                do {
                    line = reader.readLine();
                    if (null != line) {
                        inputBuffer.append(line.trim());
                    }
                } while (line != null);
            } finally {
                reader.close();
            }
            return inputBuffer.toString().trim();
        }
    }

    private static final class BufferedServletInputStream extends ServletInputStream{
        private ByteArrayInputStream bais;

        public BufferedServletInputStream(ByteArrayInputStream bais) {
            this.bais = bais;
        }

        @Override
        public int available() throws IOException {
            return this.bais.available();
        }

        @Override
        public int read() throws IOException {
            return this.bais.read();
        }

        @Override
        public int read(byte[] buf, int off, int len) throws IOException {
            return this.bais.read(buf, off, len);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }

    public class TeeServletOutputStream extends ServletOutputStream {
        private final TeeOutputStream targetStream;

        public TeeServletOutputStream(OutputStream one, OutputStream two) {
            targetStream = new TeeOutputStream(one, two);
        }

        @Override
        public void write(int arg0) throws IOException {
            this.targetStream.write(arg0);
        }

        @Override
        public void flush() throws IOException {
            super.flush();
            this.targetStream.flush();
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.targetStream.close();
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

    }

    public class BufferedResponseWrapper implements HttpServletResponse {
        HttpServletResponse original;
        TeeServletOutputStream tee;
        ByteArrayOutputStream bos;

        public BufferedResponseWrapper(HttpServletResponse response) {
            original = response;
        }

        public String getContent() {
            return null == bos ? null : bos.toString();
        }

        public PrintWriter getWriter() throws IOException{
            return original.getWriter();
        }

        public ServletOutputStream getOutputStream() throws IOException {
            if (tee == null) {
                bos = new ByteArrayOutputStream();
                tee = new TeeServletOutputStream(original.getOutputStream(), bos);
            }
            return tee;
        }


        @Override
        public void setCharacterEncoding(String s) {
            original.setCharacterEncoding(s);
        }

        @Override
        public void setContentLength(int len) {
            original.setContentLength(len);
        }

        @Override
        public void setContentLengthLong(long l) {
            original.setContentLengthLong(l);
        }

        @Override
        public void setContentType(String s) {
            original.setContentType(s);
        }

        @Override
        public void setBufferSize(int size) {
            original.setBufferSize(size);
        }

        @Override
        public int getBufferSize() {
            return original.getBufferSize();
        }

        @Override
        public void flushBuffer() throws IOException {
            tee.flush();
        }

        @Override
        public void resetBuffer() {
            original.resetBuffer();
        }

        @Override
        public boolean isCommitted() {
            return original.isCommitted();
        }

        @Override
        public void reset() {
            original.reset();
        }

        @Override
        public void setLocale(Locale locale) {
            original.setLocale(locale);
        }

        @Override
        public Locale getLocale() {
            return original.getLocale();
        }

        @Override
        public String getCharacterEncoding() {
            return original.getCharacterEncoding();
        }

        @Override
        public String getContentType() {
            return original.getContentType();
        }

        @Override
        public void addCookie(Cookie cookie) {
            original.addCookie(cookie);
        }

        @Override
        public boolean containsHeader(String name) {
            return original.containsHeader(name);
        }

        @Override
        public String encodeURL(String url) {
            return original.encodeURL(url);
        }

        @Override
        public String encodeRedirectURL(String url) {
            return original.encodeRedirectURL(url);
        }

        @Override
        public void sendError(int sc, String msg) throws IOException {
            original.sendError(sc, msg);
        }

        @Override
        public void sendError(int sc) throws IOException {
            original.sendError(sc);
        }

        @Override
        public void sendRedirect(String location) throws IOException {
            original.sendRedirect(location);
        }

        @Override
        public void setDateHeader(String name, long date) {
            original.setDateHeader(name, date);
        }

        @Override
        public void addDateHeader(String name, long date) {
            original.addDateHeader(name, date);
        }

        @Override
        public void setHeader(String name, String value) {
            original.setHeader(name, value);
        }

        @Override
        public void addHeader(String name, String value) {
            original.addHeader(name, value);
        }

        @Override
        public void setIntHeader(String name, int value) {
            original.setIntHeader(name, value);
        }

        @Override
        public void addIntHeader(String name, int value) {
            original.addIntHeader(name, value);
        }

        @Override
        public void setStatus(int sc) {
            original.setStatus(sc);
        }

        @Override
        public int getStatus() {
            return original.getStatus();
        }

        @Override
        public String getHeader(String arg0) {
            return original.getHeader(arg0);
        }

        @Override
        public Collection<String> getHeaders(String arg0) {
            return original.getHeaders(arg0);
        }

        @Override
        public Collection<String> getHeaderNames() {
            return original.getHeaderNames();
        }
    }
    */
}
