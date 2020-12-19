import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

class PreferenceMatch {
	public static void main(String[] args) {
     
        try {
           Scanner input = new Scanner(new File("input1.txt"));
            final int COUNT = input.nextInt(); // takes number and state variable count
            int[][] menPreferences = new int[COUNT][COUNT]; //array to store men preference
            int[][] womenPreferences = new int[COUNT][COUNT]; //array to store women preference

            for(int i = 0; i < COUNT; i++){ //going to row and coloumn for men
                for(int j=0; j < COUNT; j++) {
                menPreferences[i][j] = input.nextInt();//assigning value to array
                }
            }

            for(int i = 0; i < COUNT; i++){// going through row and colmn for women
                for(int j=0; j < COUNT; j++) {
                womenPreferences[i][j] = input.nextInt();//matrix is loaded file read
                }
            }

            int[] menMatches = new int[COUNT]; // creating a array for men match
            for (int m = 0; m < COUNT; m++) {
                menMatches[m] = m+1;
            }
            // permuting the preference and checking if it's stable using algorithm. 
            int stableMatches = 0;//varaible to keep track if the match is stable and if it is stable will be incremented by 1
            int[] c = new int[COUNT];
            if(isStable(menMatches, menPreferences, womenPreferences)) 
                stableMatches++;

            for(int i = 0; i < COUNT;) {
                if (c[i] < i){ 
                    if(i % 2 == 0) {
                        //swaping the array of men match to gerenrate permutation
                        int temp = menMatches[0]; //creating temperory array to swap
                        menMatches[0] = menMatches[i];
                        menMatches[i] = temp;
                    } else {
                      int temp = menMatches[c[i]];
                        menMatches[c[i]] = menMatches[i];
                        menMatches[i] = temp;
                    }

                    if(isStable(menMatches, menPreferences, womenPreferences))
                        stableMatches++;
                  
                    c[i] += 1;
                    i = 0;
                } else {
                    c[i] = 0;
                    i++;
                }
            }
          
          System.out.println(stableMatches);
          
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    //helper method to keep track if current permutation is stable match
	public static boolean isStable(int[] menMatches, int[][] menPreferences , int[][] womenPreferences){ 
        for(int m = 0; m < menMatches.length; m++) {
			int actualWoman = menMatches[m]; 
          	int preferredWoman = menPreferences[m][0];
                                                   
            if (actualWoman != preferredWoman){ //check to see if man got his preferred woman
            	int actualMan = m + 1;
                  int w = actualWoman - 1; 
                  
                int preferredMan = womenPreferences[w][0];
              	if (actualMan != preferredMan) { //check to see if woman got her preferred man
                  	return false;
                }
            }
        }
      
      return true;
    }
}
                      
                  	
                
      