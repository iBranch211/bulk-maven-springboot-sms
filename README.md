# bulk-sms
This is code for a bulk messaging web application that uses the Spring Boot framework. This project was later edited to be fit to be used in a University setting. The project is also using Twillio API for its messaging service.
To use it first create a Twillio Account. Then proceed to add details from their into your applications.properties file.
Also install Postgres SQL(create a database called "register") and Postman(For sending post requests). 
To use the login page and security features add the following code after .csrf().disable() :-
.authorizeRequests()
                    .antMatchers("/api/v*/registration/**")
                    .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();
