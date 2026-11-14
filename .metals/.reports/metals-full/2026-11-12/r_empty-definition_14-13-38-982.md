error id: file:///C:/Users/franc/Desktop/elevatormanager/app/database/DatabaseConfig.scala:Configuration#
file:///C:/Users/franc/Desktop/elevatormanager/app/database/DatabaseConfig.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/inject/Configuration#
	 -play/api/Configuration#
	 -Configuration#
	 -scala/Predef.Configuration#
offset: 477
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/database/DatabaseConfig.scala
text:
```scala
package database

// This file is part of the Lift Manager project.
// this import is used to inject the database configuration into the application

// standard dependencies injection (@Inject)
import javax.inject._
// Allow to read the configuration from the application.conf file
import play.api.Configuration
//Bind the app to ORACLE database
import slick.jdbc.jdbcProfile

@Singleton
class DatabaseConfig @Inject()(
    
){
    configuration: Configuration@@
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 