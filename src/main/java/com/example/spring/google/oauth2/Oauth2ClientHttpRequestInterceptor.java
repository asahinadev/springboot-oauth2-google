package com.example.spring.google.oauth2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Oauth2ClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		// リクエストの中身をログに落とす
		log.info("\nURI={}, \nHEADERS={}, \\Body={}", //
				request.getURI(), request.getHeaders(), new String(body));

		// レスポンスを取得する
		ClientHttpResponse response = new BufferingClientHttpResponseWrapper(execution.execute(request, body));

		// レスポンスのボディを読み込む
		StringWriter body2 = new StringWriter();
		PrintWriter writer = new PrintWriter(body2);
		try (Scanner in = new Scanner(response.getBody());) {
			while (in.hasNextLine()) {
				writer.println(in.nextLine());
			}
		}

		// レスポンスの中身をログに落とす
		log.info("Status={} Headers={}, Body={}", response.getStatusText(), response.getHeaders(), body2);

		return response;
	}

	private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

		private final ClientHttpResponse response;

		@Nullable
		private byte[] body;

		BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
			this.response = response;
		}

		@Override
		public HttpStatus getStatusCode() throws IOException {
			return this.response.getStatusCode();
		}

		@Override
		public int getRawStatusCode() throws IOException {
			return this.response.getRawStatusCode();
		}

		@Override
		public String getStatusText() throws IOException {
			return this.response.getStatusText();
		}

		@Override
		public HttpHeaders getHeaders() {
			return this.response.getHeaders();
		}

		@Override
		public InputStream getBody() throws IOException {
			if (this.body == null) {
				this.body = StreamUtils.copyToByteArray(this.response.getBody());
			}
			return new ByteArrayInputStream(this.body);
		}

		@Override
		public void close() {
			this.response.close();
		}

	}
}
