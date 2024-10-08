# Simple Kafka producer and consumer

## Overview

This project represent how to build simple `Kafka` producer and consumer using 
the `Spring Boot`.

Usually you are required to use the properties and configuration code to initialise 
the `Kafka` stuff. 
Spring Boot help do it for you so you can just set configuration in the standard
configuration files and have pretty simple Configuration Beans which do the
`Kafka client` configuration for you.

That is simple part. But what is more interesting is the `Integration Test` which 
could help you to test your `Kafka` producers and consumers even do not having access
to the real `Kafka cluster`.

It is based on `EmbeddedKafka` and let you quickly test how you messages are 
serialised and being sent to/from a topic.


## Design solutions

The real `Kafka` connection is usually based on more sophisticated technologies
like SSL and requires some additional artifacts like `SSL certificate keystore` but 
we do not need it for testing and can keep it more simple.

Therefore we keep generic configuration on the `application.yml` while specific 
to the real connection in the `application-default.yml`. It would be used if no
other profiles would be set.

The tests should be run with `SPRING_PROFILES_ACTIVE=test` so it won't fall back to
the `default` but instead will apply the settings from `application-test.yml` from 
the integration test `resources` folder.


