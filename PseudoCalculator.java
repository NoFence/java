import java.util.Scanner;
import java.util.StringTokenizer;
 
class PseudoCalculator{
        
    public static void main(String args[]) {
        String expression; // 수식을 담기 위한 문자열 변수
        int result; // 결과 값을 반환하기 위한 변수
        int i;  // 홀수 열에 담긴 배열 값 처리를 위한 반복 제어 변수
        int j; // 짝수 열에 담긴 배열 값 처리를 위한 반복 제어 변수
        boolean state;  // 상태 정보를 반환하기 위해 처리 되는 플래그 변수
        
        while(true){
            
         j=1; 
          
        expression = inputExpression();
        
       
        StringTokenizer tokenizedString = new StringTokenizer(expression,"+-*/",true); 
        int numberOfToken = tokenizedString.countTokens();  // 토큰의 갯수를 구하다
        
        String tokenArray[]; // 토큰을 담을 배열을 생성하다
        tokenArray = new String[numberOfToken]; 
        
        int operandArray[] = new int[numberOfToken]; // 피연산자를 담을 배열을 생성하다
        
        state = transformTokenToInteger(tokenArray,operandArray,tokenizedString,numberOfToken);   // 예외 사항에 대한 상태 정보를 담다
        if(!state)
           continue;  // 예외 상태 발생 시 계속해서 수식을 입력 받다
        
        result = getResult(operandArray,tokenArray,numberOfToken);  // 수식에 대한 처리 과정을 반환 값으로 넘겨 받다
        outputExpression(result);  // 반환 된 값을 출력하다
        
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
                 operandArray[i] = Integer.parseInt(tokenArray[i]);  // 토큰 배열에 담겨 있는 값이 숫자가 아니라면 예외가 발생, 이에 대한 예외 처리를 하다
            } 
        
       
         catch (Exception e) {
            
                
                // 연산자는 항상 홀수 열에 존재하여야 한다
                if ( (i%2) == 0 ){
                        if ("+".equals(tokenArray[i]) || "*".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "/".equals(tokenArray[i])){
                                System.out.println("올바른 값을 입력해 주세욧!");
                                System.out.println("연산자가 순서가 올바르지 않습니다.");
                                System.out.println();
                                state = false;
                                break;
                }
                  
                // 사칙 연산자가 아닌 모든 기호는 예외 처리의 대상이다
                if ( !("*".equals(tokenArray[i]) || "/".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "+".equals(tokenArray[i])  )  ) {
                    System.out.println("올바른 값을 입력해 주세욧!");
                    System.out.println("연산자와 숫자를 제외한 문자는 입력 될 수 없습니다.");
                    System.out.println();
                    state = false;
                    break;
                           
                }
         }
            
        }
           
         //홀수 열에 피연산자만 담을 수 있도록 하다 
         if ("+".equals(tokenArray[i]) || "*".equals(tokenArray[i]) || "-".equals(tokenArray[i]) || "/".equals(tokenArray[i])){
                continue;
         }
           
        
          
    }
        
       
        return state;
}
    
    public static int getResult(int operandArray[],String tokenArray[], int numerOfToken){
        int result; // 연산을 통해 변경 된 값을 누적하는 변수
        int j=1; // 홀수 열에 담긴 배열의 값 처리를 위한 반복 제어 변수
        int i; // 짝수 열에 담긴 배열의 값 처리를 위한 반복 제어 변수
        
        result = operandArray[0];   // 피연산자 배열에 담긴 첫번째 원소를 담다.
            
            
            // 짝수 열에 담긴 숫자를 홀수 열에 담긴 연산자를 통해 연산하여 변경 된 값을 누적하다
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
