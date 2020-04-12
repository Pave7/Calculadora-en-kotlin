package com.example.familiavelasco.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Numeros que son llamados desde el XML en el MainActivity Programación de los eventos funcion setOnClickListener
        //Al momento de dar clic los botones toman los valores que se encuentra en cada uno
        tvUno.setOnClickListener { appendOnExpresstion ("1",true)}
        tvDos.setOnClickListener { appendOnExpresstion ("2",true)}
        tvTres.setOnClickListener { appendOnExpresstion ("3",true)}
        tvCuatro.setOnClickListener { appendOnExpresstion ("4",true)}
        tvCinco.setOnClickListener { appendOnExpresstion ("5",true)}
        tvSeis.setOnClickListener { appendOnExpresstion ("6",true)}
        tvSiete.setOnClickListener { appendOnExpresstion ("7",true)}
        tvOcho.setOnClickListener { appendOnExpresstion ("8",true)}
        tvNueve.setOnClickListener { appendOnExpresstion ("9",true)}
        tvZero.setOnClickListener { appendOnExpresstion ("0",true)}
        tvDot.setOnClickListener { appendOnExpresstion (".",true)}
       // operaciones basicas (+,-,*,/)
        // Al momento de dar clic los botones toman los valores que se encuentra en cada uno realizando las respectivas operaciones

        tvMas.setOnClickListener { appendOnExpresstion ("+",false)}
        tvMenos.setOnClickListener { appendOnExpresstion ("-",false)}
        tvMultiplicacion.setOnClickListener { appendOnExpresstion ("*",false)}
        tvDivision.setOnClickListener { appendOnExpresstion ("/",false)}
        tvAbierto.setOnClickListener { appendOnExpresstion ("(",false)}
        tvCerrado.setOnClickListener { appendOnExpresstion (")",false)}

        // Evento de limpiar
        // Al momento de realizar la operación y si se quiere realizar otra esta funcion lo que hace es limpiar el tablero
        //Para introducir otros números para realizar la operación

        tvLimpiar.setOnClickListener{
            tvExpresiones.text=""
            tvResultado.text=""
        }

        //Función para borrar un número quedo mal dentro de la operacion o un sino antes de ser ejecutada
        tvBack.setOnClickListener {
            val string = tvExpresiones.text.toString()
            if(string.isNotEmpty()){ // esto se realiza cuando el elemento no esta vacio y toma elemento a eliminar
                tvExpresiones.text= string.substring(0,string.length-1)
            }

            tvResultado.text=""
        }

         tvEquals.setOnClickListener {
             try{
                 val expression = ExpressionBuilder(tvExpresiones.text.toString()).build()
                 val result =expression.evaluate()
                 val longResult = result.toLong()
                 if(result == longResult.toDouble()) // muestra los resultados si es decimal  o en el caso dado un entero dependiendo de la operación que ingrese el usuario
                     tvResultado.text = longResult.toString()  // muestra el resultado dependiendo de la operación ejecutada
                 else
                     tvResultado.text=result.toString()


             }catch (e:Exception){ // muestra un mensaje en el caso de  que la operacion sea fallida
                 Log.d("Exception","message:"+e.message)

             }
         }
    }

    fun appendOnExpresstion(string: String, canClear: Boolean){
        if(tvResultado.text.isNotEmpty()){ // Toma los espacios de los elemento siempre y cuando no este vacia
            tvExpresiones.text=""
        }

        if(canClear){
            tvResultado.text ="" // muestra los procesos de las operaciones ejecutadas en la calculadora
            tvExpresiones.append(string)
        }else {
            tvExpresiones.append(tvResultado.text)
            tvExpresiones.append(string)
            tvResultado.text = ""
        }

    }

}
