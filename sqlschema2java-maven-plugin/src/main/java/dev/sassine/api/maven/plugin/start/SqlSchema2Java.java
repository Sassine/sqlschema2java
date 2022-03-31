package dev.sassine.api.maven.plugin.start;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import dev.sassine.api.structure.Sqlschema2Java;

@Mojo(name = "sqlschema2java", defaultPhase = LifecyclePhase.NONE)
public class SqlSchema2Java extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;
	
    @Parameter(property = "sql.source.directory" , required = true, readonly = true)
    String sourceDirectory;
    
    @Parameter(property = "sql.type.is.postgres" , defaultValue = "false", required = false, readonly = true)
    Boolean isPostgres;
    
    @Parameter(property = "sql.type.auto.increment.enabled" , defaultValue = "true", required = false, readonly = true)
    Boolean useAutoIncrement;
   
    @Parameter(property = "log.debug.enabled" , defaultValue = "false", required = false, readonly = true)
    String debugEnabled;
    

    
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(" ");
        new Sqlschema2Java().compile(sourceDirectory, isPostgres, useAutoIncrement);
        getLog().info(" ");
    }
    
}
