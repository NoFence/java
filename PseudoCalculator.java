import java.util.Scanner;
import java.util.StringTokenizer;
 
class PseudoCalculator{
        
    public static void main(String args[]) {
        String expression;
        int result;
        int i;  
        int j;
        boolean state;
        
        while(true){
            
         j=1; 
          
        expression = inputExpression();
        
       
        StringTokenizer tokenizedString = new StringTokenizer(expression,"+-*/",true);
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
       
        System.out.println("수식을 입력해 주세욧 => ");
        expression = sc.next();
        
        return expression;
    }
    
    public static void outputExpression(int result){
        System.out.println(result);
        System.out.println();
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
                    System.out.println("올바른 값을 입력해 주세욧!");
                    System.out.println("연산자와 숫자를 제외한 문자는 입력 될 수 없습니다.");
                    System.out.println();
                    state = false;
                    break;
                           
                }
                
                if ( (i%2) == 0 ){
                        if ("+".equals(tokenArray[i]) || "*".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "/".equals(tokenArray[i])){
                                System.out.println("올바른 값을 입력해 주세욧!");
                                System.out.println("연산자는 연속으로 두 개 이상 입력 될 수 없습니다.");
                                System.out.println();
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
                            System.out.println("올바른 값을 입력해 주세욧!");
                            System.out.println("값을 0으로 나눌 수 없습니다.");
                            System.out.println();
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
