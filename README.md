# SQLSchema2Java  [![Open Source Love svg1](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)


[![Publish package to GitHub Packages](https://github.com/Sassine/sqlschema2java/actions/workflows/deploy.yml/badge.svg)](https://github.com/Sassine/sqlschema2java/actions/workflows/deploy.yml) 
[![CodeQL main](https://github.com/Sassine/sqlschema2java/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/Sassine/sqlschema2java/actions/workflows/codeql-analysis.yml)
[![GitHub release](https://img.shields.io/github/release/Sassine/sqlschema2java.svg)](https://GitHub.com/Sassine/sqlschema2java/releases/)

[![Coverage Status](https://coveralls.io/repos/github/Sassine/sqlschema2java/badge.svg?branch=develop)](https://coveralls.io/github/Sassine/sqlschema2java?branch=develop)

![Logo SQL2JAVA  and website adress sassine.dev/sqlschema2java](https://sassine.dev/assets/images/SQLSchema2Java_Logo2.png)


_sqlschema2java_ generates Java types from SQLSchema and annotate those types for data-binding with Jackson 2.x, Javax Persistence and Spring Repository.

You can use sqlschema2java as a Maven plugin. 
[**Try at the sample project exemple**](https://github.com/Sassine/sqlschema2java/tree/main/sqlschema2java-example)

[**Read Getting Started**](https://github.com/Sassine/sqlschema2java/wiki/Getting-Started)

## Implementation

#### A very simple Maven example:
```xml 
pom.xml
 <plugins>
            <plugin>
                <groupId>dev.sassine.api</groupId>
                <artifactId>sqlschema2java-maven-plugin</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>sqlschema2java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <sourceDirectory>/user/home/example.sql</sourceDirectory>
                    <packageName>dev.sassine.api</packageName>
                    <useAutoIncrement>true</useAutoIncrement>
                    <isPostgres>false</isPostgres>
                </configuration>
            </plugin>
</plugins>

<repositories>
	<repository>
		<id>github</id>
		<name>GitHub Packages SQLSchema2Java</name>
		<url>https://maven.pkg.github.com/Sassine/sqlschema2java</url>
	</repository>
</repositories>
```
###  Parameters
| Name | required |  type | description |
|--|--|--|--|
| sourceDirectory  | true | String | SQL file path |
| packageName | true | String | package name with points that will be generated |
| useAutoIncrement| false | Boolean| disable or enable whether the primary key will have its value generated automatically |
| isPostgres| true | Boolean | disable or enable query conversion compatible with postgres database |


#####  Run command 
```bash
mvn dev.sassine.api:sqlschema2java-maven-plugin:generate
```
Successful ✨

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/P5P8C2H8Q)

## License

[![GitHub license](https://img.shields.io/github/license/Sassine/sqlschema2java.svg)](https://github.com/Sassine/sqlschema2java/blob/master/LICENSE)
