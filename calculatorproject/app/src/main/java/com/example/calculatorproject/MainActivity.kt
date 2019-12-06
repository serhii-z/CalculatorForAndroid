package com.example.calculatorproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator2.*

class MainActivity : AppCompatActivity() {
    var number1: String = "";
    var number2: String = "";
    var operation: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator2)
    }

    fun onClearClick(view: View){
        Clear();
        numberField.setText("0");
    }

    fun onNumberClick(view: View){
        var b: Button = view as Button;
        var edit: String = numberField.getText().toString();
        if(operation == ""){
            if(edit == "0"){
                number1 = b.text.toString();
            }
            else{
                number1 += b.text.toString();
            }
            numberField.setText(number1);
            firstField.setText(number1);
        }
        else{
            number2 += b.text.toString();
            numberField.setText(number2);
        }
    }

    fun onOperationClick(view: View){
        var o : Button = view as Button;
        var temp = o.text.toString();
        if(temp == "="){
            var result: String = Calculete(number1, number2, operation);
            numberField.setText(result);
            firstField.setText(number1);
            operationField.setText(operation);
        }
        else{
            if(number1 != ""){
                operation = temp;
                operationField.setText(operation);
            }
        }
    }

    fun Calculete(firstNumber: String, secondNumber: String, operation1: String): String{
        var n1: Double = 0.0;
        var n2: Double = 0.0;
        var result: String = "";
        try{
            n1 = firstNumber.toDouble();
            n2 = secondNumber.toDouble();
            if(n2 != 0.0){
                when(operation1){
                    "*" -> {
                        var res: Double = n1*n2;
                        if(DefineAnInteger(res)){
                            result = res.toInt().toString();
                        }
                        else{
                            result = res.toString();
                        }
                    }
                    "/" ->{
                        var res: Double = n1/n2;
                        if(DefineAnInteger(res)){
                            result = res.toInt().toString();
                        }
                        else{
                            result = res.toString();
                        }
                    }
                    "+" -> {
                        var res: Double = n1+n2;
                        if(DefineAnInteger(res)){
                            result = res.toInt().toString();
                        }
                        else{
                            result = res.toString();
                        }
                    }
                    "-" -> {
                        var res: Double = n1-n2;
                        if(DefineAnInteger(res)){
                            result = res.toInt().toString();
                        }
                        else{
                            result = res.toString();
                        }
                    }
                }
            }
            else{
                result = "Ошибка деления на ноль";
                Clear();
            }
        }
        catch (e: Exception){
            result = "Введено не число";
            Clear();
        }
        number1 = result;
        operation = "";
        return result
    }

    fun DefineAnInteger(result: Double): Boolean{
        var temp: Double = result;
        temp *= 10.0;
        var str:String = temp.toString();
        str = str.substringBefore('.');
        temp = str.toDouble();
        temp /= 10;
        var temp2: Int = temp.toInt();
        var temp3: Double = temp - temp2;
        if(temp3 < 0.00000000001){
            return true;
        }
        else{
            return false;
        }
    }

    fun Clear(){
        number1 = "";
        number2 = "";
        operation = "";
        operationField.setText("");
        firstField.setText("");
    }
}
