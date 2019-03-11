package org.avbravo.jmoordbjsfgenerator.anotations.lector ;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
*
* @author avbravo
*/


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface AnotacionRelaciones {

 String tabla();

 String columna();

String clase();

String tipo_relacion() default "";

String regla_actualizacion() default "";

/*
 * mysql:
 * 0 cascada
* 3 restringir
 * postgresql :
 * 1 restringir
 * 0 cascada
 */

String regla_eliminacion() default "";
/*
 * mysql:
* 0 cascada
 * 3 restringir
* postgresql:
 * 0 cascada
 * 1 restringuir
 */

int key_seq() default 0;

String nombre_relacion() default "";
}

