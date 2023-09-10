package com.security.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetaiilsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetaiilsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("ADMIN", "USER")
        .antMatchers("/").permitAll()
        .and().formLogin();
  }
}
// Note
/*
 * 1. UserDetails + JPA သုံ;မယ်ဆိုရင် ကိုယ်ပိုင် UserDetails တည်ဆောက်ပေ;ရမယ်
 * (JDBC + LDAP တို လုပ်စရာမလိုပါ )
 * auth.userDetailsService(userDetaiilsService); အဲ့လိုရေ;လိုက်ရင် custome
 * DetailsService ထဲက method ကို လှမ်;ခေါ်နေမှာပါ
 * တနည်;အာ;ဖြင့် ကိုယ်ဟာကိုယ်သုံ;မယ်ဆိုတဲ့သဘော
 * 
 * 2.userDetailsService အနေနဲ့ userDetails Object နဲ userDetailsService
 * တည်ဆောက်ပေ;ရတယ်
 * 3.userDetailsService မှာတော့ UserDetailsService ကို impl လုပ်ပြီ;
 * loadByUserName နဲ့ useDetails object ကို return ပြန်ပေ;ရတယ်
 * ဆိုလိုချင်တာက userDetailsService ကပွဲစာ; တကယ် လုပ်မှာက UserDetails သူထဲမှာ
 * method တွေကို ov လုပ်ပြီ;သတ်မှတ်ပေ;ရတယ်
 * 
 */
