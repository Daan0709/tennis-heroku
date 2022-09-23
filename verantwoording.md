# Verantwoording

## Type opdracht

Het project van mijn groepje (403 Axians) heeft al een enorme infrastructuur opgebouwd die geregeld wordt via Azure devops, kubernetes clusters, docker containers e.d.
Onze CSC'er heeft zich hiermee bezig gehouden, maar het deployen zelf is nog niet gelukt. Wel is het zo dat wanneer wij als SD'ers in de code gaan werken, en we een commit naar main pushen, het via een CI/CD pipeline gelijk live zou komen te staan, mochten alle tests slagen.
Ik heb er zelf ook nog voor gekozen om toch maar ook de tennis app op een externe service te hosten, in dit geval Heroku.

## Hosten op Heroku

De tennis app staat live op Heroku. Ik heb geen frontend, dus de webpagina zelf is niet erg interessant. Ik maak in plaats daarvan via postman allemaal requests naar de host: https://tennis-app-inno.herokuapp.com/

zoals hieronder te zien is:
![Postman Example](/images/postman_example.gif)

Om nog even te bevestigen dat dit niet lokaal is, laat ik ook zien dat requests via localhost:8080 niet werken.


Om dit voor elkaar te krijgen, moest ik als eerst een system.properties file aanmaken met daarin de correcte java versie aangegeven:

![Java Version](/images/java_version.png)

Toen ik dat eenmaal gedaan en gepushed had, was het vrij eenvoudig. Even een applicatie aanmaken op heroku.com met de juiste repository.

Om een soort van CI/CD na te bootsen, heb ik automatic deploys aangezet, wat ervoor zorgt dat elke push naar main een nieuwe versie deployed.

![Automatic Deploys](/images/automatic_deploy.png)

Daarnaast heb ik ook een plugin geinstalleerd op mijn project die ervoor zorgt dat al mijn tests gerund worden tijdens het deployen van de app.
Mijn pom.xml ziet er dan ook als volgt uit:

![pom.xml](/images/pomxml.png)

Een snapshot van mijn build log bewijzen ook dat de tests daadwerkelijk gerund worden:

![Tests Snapshot](/images/test_examples.png)

Ik heb ook de gehele build log toegevoegd:

<details>
    <summary>Build Log</summary>



        -----> Building on the Heroku-22 stack

        -----> Using buildpack: heroku/java

        -----> Java app detected

        -----> Installing OpenJDK 17... done

        -----> Executing Maven

       $ ./mvnw -DskipTests clean dependency:list install

       [INFO] Scanning for projects...

       [INFO] 

       [INFO] --------------------------< nl.hu.sd:tennis >---------------------------

       [INFO] Building tennis 0.0.1-SNAPSHOT

       [INFO] --------------------------------[ jar ]---------------------------------

       [INFO] 

       [INFO] --- maven-clean-plugin:3.2.0:clean (default-clean) @ tennis ---

       [INFO] 

       [INFO] --- maven-dependency-plugin:3.3.0:list (default-cli) @ tennis ---

       [INFO] 

       [INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ tennis ---

       [INFO] Using 'UTF-8' encoding to copy filtered resources.

       [INFO] Using 'UTF-8' encoding to copy filtered properties files.

       [INFO] Copying 1 resource

       [INFO] Copying 0 resource

       [INFO] 

       [INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ tennis ---

       [INFO] Changes detected - recompiling the module!

       [INFO] Compiling 16 source files to /tmp/build_803d45b5/target/classes

       [INFO] 

       [INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ tennis ---

       [INFO] Using 'UTF-8' encoding to copy filtered resources.

       [INFO] Using 'UTF-8' encoding to copy filtered properties files.

       [INFO] skip non existing resourceDirectory /tmp/build_803d45b5/src/test/resources

       [INFO] 

       [INFO] --- maven-compiler-plugin:3.10.1:testCompile (default-testCompile) @ tennis ---

       [INFO] Changes detected - recompiling the module!

       [INFO] Compiling 7 source files to /tmp/build_803d45b5/target/test-classes

       [INFO] 

       [INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ tennis ---

       [INFO] 

       [INFO] -------------------------------------------------------

       [INFO]  T E S T S

       [INFO] -------------------------------------------------------

       [INFO] Running nl.hu.sd.tennis.TennisApplicationTests

       12:05:33.198 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating CacheAwareContextLoaderDelegate from class [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]

       12:05:33.221 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating BootstrapContext using constructor [public org.springframework.test.context.support.DefaultBootstrapContext(java.lang.Class,org.springframework.test.context.CacheAwareContextLoaderDelegate)]

       12:05:33.282 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating TestContextBootstrapper for test class [nl.hu.sd.tennis.TennisApplicationTests] from class [org.springframework.boot.test.context.SpringBootTestContextBootstrapper]

       12:05:33.298 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Neither @ContextConfiguration nor @ContextHierarchy found for test class [nl.hu.sd.tennis.TennisApplicationTests], using SpringBootContextLoader

       12:05:33.306 [main] DEBUG org.springframework.test.context.support.AbstractContextLoader - Did not detect default resource location for test class [nl.hu.sd.tennis.TennisApplicationTests]: class path resource [nl/hu/sd/tennis/TennisApplicationTests-context.xml] does not exist

       12:05:33.306 [main] DEBUG org.springframework.test.context.support.AbstractContextLoader - Did not detect default resource location for test class [nl.hu.sd.tennis.TennisApplicationTests]: class path resource [nl/hu/sd/tennis/TennisApplicationTestsContext.groovy] does not exist

       12:05:33.307 [main] INFO org.springframework.test.context.support.AbstractContextLoader - Could not detect default resource locations for test class [nl.hu.sd.tennis.TennisApplicationTests]: no resource found for suffixes {-context.xml, Context.groovy}.

       12:05:33.308 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils - Could not detect default configuration classes for test class [nl.hu.sd.tennis.TennisApplicationTests]: TennisApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.

       12:05:33.393 [main] DEBUG org.springframework.test.context.support.ActiveProfilesUtils - Could not find an 'annotation declaring class' for annotation type [org.springframework.test.context.ActiveProfiles] and class [nl.hu.sd.tennis.TennisApplicationTests]

       12:05:33.485 [main] DEBUG org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider - Identified candidate component class: file [/tmp/build_803d45b5/target/classes/nl/hu/sd/tennis/TennisApplication.class]

       12:05:33.488 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Found @SpringBootConfiguration nl.hu.sd.tennis.TennisApplication for test class nl.hu.sd.tennis.TennisApplicationTests

       12:05:33.593 [main] DEBUG org.springframework.boot.test.context.SpringBootTestContextBootstrapper - @TestExecutionListeners is not present for class [nl.hu.sd.tennis.TennisApplicationTests]: using defaults.

       12:05:33.594 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]

       12:05:33.615 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@258d79be, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@14f9390f, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@6c0d7c83, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@176b75f7, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@5965be2d, org.springframework.test.context.support.DirtiesContextTestExecutionListener@409c54f, org.springframework.test.context.transaction.TransactionalTestExecutionListener@3e74829, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener@4f6f416f, org.springframework.test.context.event.EventPublishingTestExecutionListener@3b8f0a79, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@71e693fa, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@48793bef, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@7d286fb6, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@3eb77ea8, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@7b8b43c7, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@7aaca91a]

       12:05:33.619 [main] DEBUG org.springframework.test.context.support.AbstractDirtiesContextTestExecutionListener - Before test class: context [DefaultTestContext@436c81a3 testClass = TennisApplicationTests, testInstance = [null], testMethod = [null], testException = [null], mergedContextConfiguration = [WebMergedContextConfiguration@3561c410 testClass = TennisApplicationTests, locations = '{}', classes = '{class nl.hu.sd.tennis.TennisApplication}', contextInitializerClasses = '[]', activeProfiles = '{}', propertySourceLocations = '{}', propertySourceProperties = '{org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true}', contextCustomizers = set[org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@10959ece, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3e6ef8ad, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@44821a96, org.springframework.boot.test.autoconfigure.actuate.metrics.MetricsExportContextCustomizerFactory$DisableMetricExportContextCustomizer@1e6454ec, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@932bc4a, org.springframework.boot.test.context.SpringBootTestArgs@1, org.springframework.boot.test.context.SpringBootTestWebEnvironment@49993335], resourceBasePath = 'src/main/webapp', contextLoader = 'org.springframework.boot.test.context.SpringBootContextLoader', parent = [null]], attributes = map['org.springframework.test.context.web.ServletTestExecutionListener.activateListener' -> true]], class annotated with @DirtiesContext [false] with mode [null].

       

         .   ____          _            __ _ _

        /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \

       ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \

        \\/  ___)| |_)| | | | | || (_| |  ) ) ) )

         '  |____| .__|_| |_|_| |_\__, | / / / /

        =========|_|==============|___/=/_/_/_/

        :: Spring Boot ::                (v2.7.0)

       

       2022-09-23 12:05:34.088  INFO 210 --- [           main] nl.hu.sd.tennis.TennisApplicationTests   : Starting TennisApplicationTests using Java 17.0.4.1 on dfc63198-1131-4a51-97e5-c63ff0b9dc82 with PID 210 (started by u57299 in /tmp/build_803d45b5)

       2022-09-23 12:05:34.090  INFO 210 --- [           main] nl.hu.sd.tennis.TennisApplicationTests   : No active profile set, falling back to 1 default profile: "default"

       2022-09-23 12:05:35.028  INFO 210 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.

       2022-09-23 12:05:35.087  INFO 210 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 50 ms. Found 2 JPA repository interfaces.

       2022-09-23 12:05:35.775  INFO 210 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...

       2022-09-23 12:05:36.039  INFO 210 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.

       2022-09-23 12:05:36.118  INFO 210 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]

       2022-09-23 12:05:36.181  INFO 210 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.9.Final

       2022-09-23 12:05:36.365  INFO 210 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}

       2022-09-23 12:05:36.548  INFO 210 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect

       2022-09-23 12:05:37.301  INFO 210 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]

       2022-09-23 12:05:37.313  INFO 210 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'

       2022-09-23 12:05:38.091  WARN 210 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning

       2022-09-23 12:05:38.928  INFO 210 --- [           main] nl.hu.sd.tennis.TennisApplicationTests   : Started TennisApplicationTests in 5.265 seconds (JVM running for 6.974)

       [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.284 s - in nl.hu.sd.tennis.TennisApplicationTests

       [INFO] Running nl.hu.sd.tennis.presentation.MatchControllerIntegrationTest

       2022-09-23 12:05:39.284  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Neither @ContextConfiguration nor @ContextHierarchy found for test class [nl.hu.sd.tennis.presentation.MatchControllerIntegrationTest], using SpringBootContextLoader

       2022-09-23 12:05:39.285  INFO 210 --- [           main] o.s.t.c.support.AbstractContextLoader    : Could not detect default resource locations for test class [nl.hu.sd.tennis.presentation.MatchControllerIntegrationTest]: no resource found for suffixes {-context.xml, Context.groovy}.

       2022-09-23 12:05:39.285  INFO 210 --- [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [nl.hu.sd.tennis.presentation.MatchControllerIntegrationTest]: MatchControllerIntegrationTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.

       2022-09-23 12:05:39.332  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration nl.hu.sd.tennis.TennisApplication for test class nl.hu.sd.tennis.presentation.MatchControllerIntegrationTest

       2022-09-23 12:05:39.341  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]

       2022-09-23 12:05:39.343  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@8ab231, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@5b3a8bea, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@6ba0dcba, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@6d7772d3, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@38394e76, org.springframework.test.context.support.DirtiesContextTestExecutionListener@37d3e740, org.springframework.test.context.transaction.TransactionalTestExecutionListener@5afba80c, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener@c4ca86a, org.springframework.test.context.event.EventPublishingTestExecutionListener@7e741d6b, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@56351c02, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@51097500, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@386f88b3, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@662e5590, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@1885905b, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@23e573e]

       

         .   ____          _            __ _ _

        /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \

       ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \

        \\/  ___)| |_)| | | | | || (_| |  ) ) ) )

         '  |____| .__|_| |_|_| |_\__, | / / / /

        =========|_|==============|___/=/_/_/_/

        :: Spring Boot ::                (v2.7.0)

       

       2022-09-23 12:05:39.413  INFO 210 --- [           main] n.h.s.t.p.MatchControllerIntegrationTest : Starting MatchControllerIntegrationTest using Java 17.0.4.1 on dfc63198-1131-4a51-97e5-c63ff0b9dc82 with PID 210 (started by u57299 in /tmp/build_803d45b5)

       2022-09-23 12:05:39.415  INFO 210 --- [           main] n.h.s.t.p.MatchControllerIntegrationTest : No active profile set, falling back to 1 default profile: "default"

       2022-09-23 12:05:39.656  INFO 210 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.

       2022-09-23 12:05:39.665  INFO 210 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 7 ms. Found 2 JPA repository interfaces.

       2022-09-23 12:05:39.961  INFO 210 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Starting...

       2022-09-23 12:05:39.962  INFO 210 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Start completed.

       2022-09-23 12:05:39.971  INFO 210 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]

       2022-09-23 12:05:39.976  INFO 210 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect

       2022-09-23 12:05:40.025  INFO 210 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]

       2022-09-23 12:05:40.025  INFO 210 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'

       2022-09-23 12:05:40.184  WARN 210 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning

       2022-09-23 12:05:40.508  INFO 210 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''

       2022-09-23 12:05:40.508  INFO 210 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''

       2022-09-23 12:05:40.511  INFO 210 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms

       2022-09-23 12:05:40.540  INFO 210 --- [           main] n.h.s.t.p.MatchControllerIntegrationTest : Started MatchControllerIntegrationTest in 1.194 seconds (JVM running for 8.586)

       [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.524 s - in nl.hu.sd.tennis.presentation.MatchControllerIntegrationTest

       [INFO] Running nl.hu.sd.tennis.presentation.PlayerControllerIntegrationTest

       2022-09-23 12:05:40.858  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Neither @ContextConfiguration nor @ContextHierarchy found for test class [nl.hu.sd.tennis.presentation.PlayerControllerIntegrationTest], using SpringBootContextLoader

       2022-09-23 12:05:40.860  INFO 210 --- [           main] o.s.t.c.support.AbstractContextLoader    : Could not detect default resource locations for test class [nl.hu.sd.tennis.presentation.PlayerControllerIntegrationTest]: no resource found for suffixes {-context.xml, Context.groovy}.

       2022-09-23 12:05:40.862  INFO 210 --- [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [nl.hu.sd.tennis.presentation.PlayerControllerIntegrationTest]: PlayerControllerIntegrationTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.

       2022-09-23 12:05:40.867  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration nl.hu.sd.tennis.TennisApplication for test class nl.hu.sd.tennis.presentation.PlayerControllerIntegrationTest

       2022-09-23 12:05:40.868  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]

       2022-09-23 12:05:40.869  INFO 210 --- [           main] .b.t.c.SpringBootTestContextBootstrapper : Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@55c78556, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@25134e01, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@12d28106, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@6a3c1b56, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@3b8ce72, org.springframework.test.context.support.DirtiesContextTestExecutionListener@3ce4eb42, org.springframework.test.context.transaction.TransactionalTestExecutionListener@3a388769, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener@3054cdd3, org.springframework.test.context.event.EventPublishingTestExecutionListener@755a4ef5, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@6eff05e7, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@1002c49e, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@62eb918, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@21a462ce, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@37e28b20, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@31428f76]

       

         .   ____          _            __ _ _

        /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \

       ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \

        \\/  ___)| |_)| | | | | || (_| |  ) ) ) )

         '  |____| .__|_| |_|_| |_\__, | / / / /

        =========|_|==============|___/=/_/_/_/

        :: Spring Boot ::                (v2.7.0)

       

       2022-09-23 12:05:40.902  INFO 210 --- [           main] .h.s.t.p.PlayerControllerIntegrationTest : Starting PlayerControllerIntegrationTest using Java 17.0.4.1 on dfc63198-1131-4a51-97e5-c63ff0b9dc82 with PID 210 (started by u57299 in /tmp/build_803d45b5)

       2022-09-23 12:05:40.902  INFO 210 --- [           main] .h.s.t.p.PlayerControllerIntegrationTest : No active profile set, falling back to 1 default profile: "default"

       2022-09-23 12:05:41.086  INFO 210 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.

       2022-09-23 12:05:41.096  INFO 210 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 10 ms. Found 2 JPA repository interfaces.

       2022-09-23 12:05:41.266  INFO 210 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Starting...

       2022-09-23 12:05:41.267  INFO 210 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Start completed.

       2022-09-23 12:05:41.277  INFO 210 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]

       2022-09-23 12:05:41.281  INFO 210 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect

       2022-09-23 12:05:41.362  INFO 210 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]

       2022-09-23 12:05:41.362  INFO 210 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'

       2022-09-23 12:05:41.547  WARN 210 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning

       2022-09-23 12:05:41.829  INFO 210 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''

       2022-09-23 12:05:41.829  INFO 210 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''

       2022-09-23 12:05:41.830  INFO 210 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms

       2022-09-23 12:05:41.857  INFO 210 --- [           main] .h.s.t.p.PlayerControllerIntegrationTest : Started PlayerControllerIntegrationTest in 0.985 seconds (JVM running for 9.903)

       [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.23 s - in nl.hu.sd.tennis.presentation.PlayerControllerIntegrationTest

       [INFO] Running nl.hu.sd.tennis.domain.PlayerTest

       [INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.048 s - in nl.hu.sd.tennis.domain.PlayerTest

       [INFO] Running nl.hu.sd.tennis.domain.MatchTest

       [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 s - in nl.hu.sd.tennis.domain.MatchTest

       [INFO] Running nl.hu.sd.tennis.application.PlayerServiceTest

       [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in nl.hu.sd.tennis.application.PlayerServiceTest

       [INFO] Running nl.hu.sd.tennis.application.MatchServiceTest

       [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s - in nl.hu.sd.tennis.application.MatchServiceTest

       2022-09-23 12:05:42.246  INFO 210 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'

       2022-09-23 12:05:42.246  INFO 210 --- [ionShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'

       2022-09-23 12:05:42.453  INFO 210 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...

       2022-09-23 12:05:42.457  INFO 210 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

       2022-09-23 12:05:42.459  INFO 210 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'

       2022-09-23 12:05:42.460  INFO 210 --- [ionShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'

       2022-09-23 12:05:42.468  INFO 210 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown initiated...

       2022-09-23 12:05:42.470  INFO 210 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-2 - Shutdown completed.

       2022-09-23 12:05:42.473  INFO 210 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'

       2022-09-23 12:05:42.473  INFO 210 --- [ionShutdownHook] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'

       2022-09-23 12:05:42.475  WARN 210 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 90121, SQLState: 90121

       2022-09-23 12:05:42.475 ERROR 210 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-212]

       2022-09-23 12:05:42.476  WARN 210 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 90121, SQLState: 90121

       2022-09-23 12:05:42.478 ERROR 210 --- [ionShutdownHook] o.h.engine.jdbc.spi.SqlExceptionHelper   : Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-212]

       2022-09-23 12:05:42.478  WARN 210 --- [ionShutdownHook] o.s.b.f.support.DisposableBeanAdapter    : Invocation of destroy method failed on bean with name 'entityManagerFactory': org.hibernate.exception.JDBCConnectionException: Unable to release JDBC Connection used for DDL execution

       2022-09-23 12:05:42.479  INFO 210 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown initiated...

       2022-09-23 12:05:42.481  INFO 210 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-3 - Shutdown completed.

       [INFO] 

       [INFO] Results:

       [INFO] 

       [INFO] Tests run: 22, Failures: 0, Errors: 0, Skipped: 0

       [INFO] 

       [INFO] 

       [INFO] --- maven-jar-plugin:3.2.2:jar (default-jar) @ tennis ---

       [INFO] Building jar: /tmp/build_803d45b5/target/tennis-0.0.1-SNAPSHOT.jar

       [INFO] 

       [INFO] --- spring-boot-maven-plugin:2.7.0:repackage (repackage) @ tennis ---

       [INFO] Replacing main artifact with repackaged archive

       [INFO] 

       [INFO] --- maven-dependency-plugin:3.3.0:copy (default) @ tennis ---

       [INFO] Configured Artifact: com.heroku:webapp-runner:9.0.30.0:jar

       [INFO] Copying webapp-runner-9.0.30.0.jar to /tmp/build_803d45b5/target/dependency/webapp-runner.jar

       [INFO] 

       [INFO] --- maven-install-plugin:2.5.2:install (default-install) @ tennis ---

       [INFO] Installing /tmp/build_803d45b5/target/tennis-0.0.1-SNAPSHOT.jar to /tmp/codon/tmp/cache/.m2/repository/nl/hu/sd/tennis/0.0.1-SNAPSHOT/tennis-0.0.1-SNAPSHOT.jar

       [INFO] Installing /tmp/build_803d45b5/pom.xml to /tmp/codon/tmp/cache/.m2/repository/nl/hu/sd/tennis/0.0.1-SNAPSHOT/tennis-0.0.1-SNAPSHOT.pom

       [INFO] ------------------------------------------------------------------------

       [INFO] BUILD SUCCESS

       [INFO] ------------------------------------------------------------------------

       [INFO] Total time:  16.725 s

       [INFO] Finished at: 2022-09-23T12:05:43Z

       [INFO] ------------------------------------------------------------------------

        -----> Discovering process types

       Procfile declares types     -> (none)

       Default types for buildpack -> web

        -----> Compressing...

       Done: 122M

        -----> Launching...

       Released v6

       https://tennis-app-inno.herokuapp.com/ deployed to Heroku

    Starting November 28th, 2022, free Heroku Dynos, free Heroku Postgres, and free Heroku Data for Redis® will no longer be available.

    If you have apps using any of these resources, you must upgrade to paid plans by this date to ensure your apps continue to run and to retain your data. For students, we will announce a new program by the end of September. Learn more at https://blog.heroku.com/next-chapter


</details>
