package com.example.spring.google.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);

		web.ignoring().antMatchers(
				// webjars
				"/webjars/**",
				// CSS ファイル
				"/css/**",
				// JavaScriptファイル
				"/js/**",
				// 画像ファイル
				"/img/**",
				// サウンドファイル
				"/sound/**",
				// WEB フォント
				"/font/**", "/fonts/**",
				// 外部ライブラリ
				"/exlib/**"
		/**/
		);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// トップページは誰でもOK(自動遷移禁止）
				.antMatchers("/").permitAll()
				// エラーページは誰でもOK(自動遷移禁止）
				.antMatchers("/error**").permitAll()
				// ログイン必須
				.anyRequest().authenticated();

		this.configure(http.formLogin());
		this.configure(http.httpBasic());
		this.configure(http.logout());
		this.configure(http.csrf());
		this.configure(http.oauth2Login());

	}

	protected void configure(FormLoginConfigurer<HttpSecurity> form) {
		form.disable();
	}

	protected void configure(LogoutConfigurer<HttpSecurity> logout) {
	}

	protected void configure(HttpBasicConfigurer<HttpSecurity> basic) {
		basic.disable();
	}

	protected void configure(CsrfConfigurer<HttpSecurity> csrf) {
		csrf.disable();
	}

	protected void configure(OAuth2LoginConfigurer<HttpSecurity> oauth) {
		oauth
				// 認証エンドポイント
				.authorizationEndpoint().and()

				// リダイレクトエンドポイント
				.redirectionEndpoint().and()

				// アクセストークンエンドポイント
				.tokenEndpoint().and()

				// ユーザー情報エンドポイント
				.userInfoEndpoint().and();

	}

}
