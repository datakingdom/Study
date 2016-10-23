/**
 * Created by SophieGao on 10/22/16.
 */
public class Test02 {
    public static void main(String[] args) {
        String cipherText = "Otjfvknou kskgnl, K mbxg iurtsvcnb ksgq hoz atv"
                + ". Vje xcxtyqrl vt ujg smewfv vrmcxctg rwqr ju vhm ytsf elwepuqyez."
                + " -Atvt hrqgse, Cnikg";
        String plainText = decrypt(cipherText);
        System.out.println(plainText);
    }
    public static String decrypt(String encrypted_message){
            if(encrypted_message == null) {return "";}

            // determine the key Alice is using
            int[] key = new int[]{8,2,5,1,2,2,0};
            StringBuilder result = new StringBuilder();
            int messLen = encrypted_message.length();
            int keyIndex = 0;

            // decrypt communicatioins char by char
            for(int i = 0; i < messLen; i++){
                char cur = encrypted_message.charAt(i);
                if(!isAlphabetic(cur)){
                    result.append(cur);
                    continue;
                }
                // if current text is alphabetic, follow the key to transfer encrypted char into original char
                char plain = (char) (cur - key[keyIndex]);

                // loop the key once the shifting finishes in one circle time which is the length of the key array
                if(++keyIndex == key.length){
                    keyIndex = 0;
                }

                // add 26 to make sure the char is still within alphabt range
                if(!isAlphabetic(plain)){
                    plain = (char)(plain + 26);
                }
                else if(Character.isUpperCase(plain) && Character.isLowerCase(cur)){
                    plain = (char)(plain + 26);
                }
                result.append(plain);
            }
            return result.toString();
        }

// check if char is alphabetic
        public static boolean isAlphabetic(char cha){
            return (cha >= 'a' && cha <= 'z') || (cha >= 'A' && cha <= 'Z');
        }


    }
