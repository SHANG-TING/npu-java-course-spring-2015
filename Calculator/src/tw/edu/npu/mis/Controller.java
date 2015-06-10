/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

/**
 *
 * @author User
 */
public class Controller {
    private final Calculator mModel;

    public Controller(Calculator model) {
        mModel = model;
    }
    
    public void Listener(String s) {
        if(s == "0" || s == "1" || s == "2" || s == "3" || s == "4" || s == "5"|| s == "6" || s == "7" || s == "8" || s == "9") {
            mModel.appendDigit(Integer.valueOf(s));
        }else if(s == ".") {
            mModel.appendDot();
        }else {
            switch(s) {
                case "C":
                    mModel.performOperation(Calculator.Operator.CLEAR);
                    break;
                case "CE":
                    mModel.performOperation(Calculator.Operator.CLEAR_ENTRY);
                    break;
                case "←":
                    mModel.performOperation(Calculator.Operator.BACKSPACE);
                    break;
                case "=":
                    mModel.performOperation(Calculator.Operator.EQUAL);
                    break;
                case "+":
                    mModel.performOperation(Calculator.Operator.PLUS);
                    break;
                case "-":
                    mModel.performOperation(Calculator.Operator.MINUS);
                    break;
                case "×":
                    mModel.performOperation(Calculator.Operator.TIMES);
                    break;
                case " ⁄":
                    mModel.performOperation(Calculator.Operator.OVER);
                    break;
                case "±":
                    mModel.performOperation(Calculator.Operator.PLUS_MINUS);
                    break;
                case "1/x":
                    mModel.performOperation(Calculator.Operator.RECIPROCAL);
                    break;
                case "%":
                    mModel.performOperation(Calculator.Operator.PERCENT);
                    break;
                case "√":
                    mModel.performOperation(Calculator.Operator.SQRT);
                    break;
                case "MS":
                    mModel.performOperation(Calculator.Operator.MEM_SET);
                    break;
                case "MR":
                    mModel.performOperation(Calculator.Operator.MEM_RECALL);
                    break;
                case "MC":
                    mModel.performOperation(Calculator.Operator.MEM_CLEAR);
                    break;
            }
        }
    }
}
