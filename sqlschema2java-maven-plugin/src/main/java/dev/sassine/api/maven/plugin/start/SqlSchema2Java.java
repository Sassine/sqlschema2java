package dev.sassine.api.maven.plugin.start;
import static dev.sassine.api.structure.Sqlschema2Java.generate;
import static java.lang.String.format;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import dev.sassine.api.structure.model.java.EnvironmentModel;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.NONE)
public class SqlSchema2Java extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;
	
    @Parameter(property = "sql.source.directory" , required = true, readonly = true)
    String sourceDirectory;
    
    @Parameter(property = "sql.source.directory" , required = true, readonly = true)
    String packageName;
    
    @Parameter(property = "sql.type.is.postgres" , defaultValue = "false", required = false, readonly = true)
    Boolean usePostgreSQL;
    
    @Parameter(property = "sql.type.auto.increment.enabled" , defaultValue = "true", required = false, readonly = true)
    Boolean useAutoIncrement;
   
    
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(format(" packageName %s - sourceDirectory %s - isPostgres %s - useAutoIncrement %s", sourceDirectory, packageName, usePostgreSQL, useAutoIncrement));
        generate(EnvironmentModel.builder()
        		.sourceDirectory(sourceDirectory)
        		.packageName(packageName)
        		.usePostgreSQL(usePostgreSQL)
        		.useAutoIncrement(useAutoIncrement)
        		.build());
    }
    
}
