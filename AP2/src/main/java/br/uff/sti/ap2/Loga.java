package br.uff.sti.ap2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//avisa que essa anotação só pode ser colada em cima de Métodos
@Target(ElementType.METHOD)
//avisa que essa anotação deve ficar visível enquanto o programa estiver rodando
@Retention(RetentionPolicy.RUNTIME)
public @interface Loga {
}