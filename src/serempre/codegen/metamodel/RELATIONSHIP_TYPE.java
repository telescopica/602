/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel;

/**
 *
 * @author Thor
 */
public enum RELATIONSHIP_TYPE {
    //this binds an instance to some other instance
    ONE_TO_ONE
    //this binds an instance to several other instances
    , ONE_TO_MANY
    //this makes a many to many relationship
    , MANY_TO_MANY
}
