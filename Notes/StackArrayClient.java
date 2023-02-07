public class StackArrayClient {
    public static void main(String[] args) {
        StackArray<String> stackArray = new StackArray<String>(10);
        for (int i=0; i<10; i++){
            stackArray.push(i+"");
        }

        // while (!stackArray.isEmpty()){
        //     System.out.println(stackArray.pop());
        // }
        
        for (String s: stackArray){
            System.out.println(s);
        }
    }
}
