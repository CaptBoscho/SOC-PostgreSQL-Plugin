# SOC-PostgreSQL-Plugin

To compile the JAR:
run `gradle fatjar` from the directory.

To upload the JAR:
Go to: http://soc-registry-service.herokuapp.com/ and click "upload". Select the JAR (it's under build/lib). ONLY do this if the JAR isn't already there on http://soc-registry-service.herokuapp.com/jars. If you need to manually update it, go to http://soc-registry-service.herokuapp.com/clear to flush out the existing JARs. You can then upload the new JAR with no problem.
