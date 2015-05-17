import java.util.Scanner;
import java.util.StringTokenizer;
 
class PseudoCalculator{
        
    public static void main(String args[]) {
        String expression;
        int result;
        int i;  // Even column
        int j; // Odd column
        boolean state;
        
        while(true){
            
         j=1;  // loop variable
        
         
        expression = inputExpression();
        
        // Define variable about string to be tokenized
        // Define delimeter with "+,*,/,-"
        StringTokenizer tokenizedString = new StringTokenizer(expression,"+-*/",true);
        
        
        // Define number of tokenized string
        int numberOfToken = tokenizedString.countTokens();
        
        String tokenArray[];
        tokenArray = new String[numberOfToken]; 
        
        int operandArray[] = new int[numberOfToken];
        
        state = transformTokenToInteger(tokenArray,operandArray,tokenizedString,numberOfToken);
        if(!state)
           continue;
        
        result = getResult(operandArray,tokenArray,numberOfToken);
        outputExpression(result);
        
        }
    
    }
    
    public static String inputExpression(){
        String expression;
        Scanner sc = new Scanner(System.in);        
       
        System.out.println("input the expression => ");
        expression = sc.next();
        
        return expression;
    }
    
    public static void outputExpression(int result){
        System.out.println(result);
    }
    
    public static boolean transformTokenToInteger(String tokenArray[], int operandArray[],StringTokenizer tokenizedString,int numberOfToken){
        int i;
        boolean state = true;
        
        for(i=0; tokenizedString.hasMoreTokens(); i++){
             
        tokenArray[i] = tokenizedString.nextToken();
        
        try {
                 operandArray[i] = Integer.parseInt(tokenArray[i]);
            } 
         catch (Exception e) {
                if ( !("*".equals(tokenArray[i]) || "/".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "+".equals(tokenArray[i])  )  ) {
                    System.out.println("Please input right value,sir!");
                    System.out.println("Any character exepet operators and digits coudn't be input.");
                    state = false;
                    break;
                           
                }
                
                if ( (i%2) == 0 ){
                        if ("+".equals(tokenArray[i]) || "*".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "/".equals(tokenArray[i])){
                                System.out.println("Please input right value,sir!");
                                System.out.println("Operators coudn't be input in rows");
                                state = false;
                                break;
                }
                  
         }
            
        }
           
         if ("+".equals(tokenArray[i]) || "*".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "/".equals(tokenArray[i])){
                continue;
         }
           
        
          
}
        
       
        return state;
    }
    
    public static int getResult(int operandArray[],String tokenArray[], int numerOfToken){
        int result;
        int j=1;
        int i;
        
        result = operandArray[0];
            
            for(i=2; i < numerOfToken; i+=2){
                if ("+".equals(tokenArray[j])){
                    result += operandArray[i];
                      
                }
         
                else if ("*".equals(tokenArray[j])) {
                    result *= operandArray[i];
                }
                
                else if ("/".equals(tokenArray[j])) {
                    try {
                            result /= operandArray[i];
                    } 
                    catch (Exception e) {
                            System.out.println("divide by zero can't be operated!");
                            System.out.println("Please input right value.");
                            result =0;
                            break;
                    }
                   
                }
                
                else{
                    result -= operandArray[i];
                }
                    
                j+=2;
            }
        return result;
    }
 
}
