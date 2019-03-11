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

public @interface AnotacionColumna {

String nombre();

String tipo() default "";

String comentario() default "";

int tamano() default 0;

int digitosDecimales() default 0;

boolean isPK() default false;

int ordinalPosicion() default 0;

String def() default "";
/*
* cuando usamos
* column_nullable = columns.getInt("NULLABLE");
* para obtener si el campo permite null
* devuelve 0 (usaremos true) cuando no permite y 1 (false) cuando permite
* 
*/

boolean isNoNulo() default false;

int num_prec_radix() default 0;

/*
* en postgresql los campos seriales serian autoincrementables
* puede almacenar YES/SI NO/NOT
*/

String is_autoincrementable() default "";

boolean isRequerido() default false;

boolean isImagen() default false;

boolean isUrl() default false;

boolean isVisible() default false;

}
