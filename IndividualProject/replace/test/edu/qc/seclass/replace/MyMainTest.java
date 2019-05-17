package edu.qc.seclass.replace;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.*
import org.junit.*;
import org.junit.rules.TemporaryFolder;

public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }
    
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //Test Cases

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    /*
    Test Case 2  		<error>
    File : File not found
     */
    @Test
    public void replaceTest1() throws Exception{
        File fileInput1 = createInputFile1();

        String args[] = {"replace -b Howdy Hello -- file1.txt file2.txt", fileInput1.getPath()};
        Main.main(args);
        String expected = "File not found";
        String actual = "File not found";
        assertEquals("File not found", expected, actual);

    }

    /*
   Test Case 3  		<error>
   Options :  Opt not found
     */
    @Test
    public void replaceTest2() throws Exception {
        File fileInput = createInputFile2();

        String args[] = {"replace -b Howdy Hello -- file1.txt file2.txt", fileInput.getPath()};
        Main.main(args);
        String expected = "file1.txt not found";
        assertEquals("file1.txt not found", expected);
    }

    /*
   Test Case 69 		(Key = 2.4.2.2.1.4.)
   File                                            :  Not empty
   Options                                         :  -i
   Parameter from                                  :  1length
   Parameter to                                    :  1length
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace sensitiveCase
     */
    @Test
    public void replaceTest3() throws Exception{
        String args[] ={"replace -i hello HELLO -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "hi";

        assertEquals("hi", expected);
    }

    /*
   Test Case 41 		(Key = 2.2.3.3.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest4() throws Exception{
        String args[] ={"replace -f hello HELLO -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "no match";

        assertEquals("no match", expected);
    }

    /*
   Test Case 42 		(Key = 2.2.3.3.3.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace replaceFrom
     */

    @Test
    public void replaceTest5() throws Exception{
        String args[] ={"replace -f hello heLLO -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "replaced";

        assertEquals("replaced", expected);
    }

    /*
   Test Case 24 		(Key = 2.1.3.4.3.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  xlength
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace backUp
     */
    @Test
    public void replaceTest6(){
        String args[] ={"replace -b j l -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "replaced from j to l";

        assertEquals("replaced from j to l", expected);
    }

    /*
   Test Case 25 		(Key = 2.1.3.5.1.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  xlength
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace backUp
     */

    @Test
    public void replaceTest7(){
        String args[] ={"replace -b d D -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "replaced from d to D";

        assertEquals("replaced from d to D", expected);
    }
    
    /*
    Test Case 9  		(Key = 2.1.2.2.1.1.)
    File                                            :  Not empty
    Options                                         :  -b
    Parameter from                                  :  1length
    Parameter to                                    :  1length
    Number of matches of the pattern in second file :  empty
    Replace Value                                   :  Replace backUp
    */
    
    @Test
    public void replaceTest8() throws Exception{
        File fileInput = createInputFile1();

        String args[] = {"replace -i Bill William -- file1.txt file2.txt", fileInput.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(fileInput.getPath());
        assertEquals("replaced",expected, actual);
    }
    /*
   Test Case 12 		(Key = 2.1.2.3.2.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  1length
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  one
   Replace Value                                   :  Replace backUp
     */
    @Test
    public void replaceTest9() throws Exception{
        File input = createInputFile3();
        String args[] = {"replace -b 'can' 'nac' -- file1.txt, file2.txt"};
        String args1[] = {"replace -i 'can' 'nac' -- file1.txt, file2.txt"};
        Main.main(args);
        Main.main(args1);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                          "It is important to know your abc and 123," +
                          "so you should study it\n" +
                          "and then repeat with me: abc and 123";

        String actual = getFileContent(input.getPath());
        assertEquals("Replaced ", expected, actual);

    }

    /*
   Test Case 68 		(Key = 2.4.2.1.2.4.)
   File                                            :  Not empty
   Options                                         :  -i
   Parameter from                                  :  1length
   Parameter to                                    :  0length
   Number of matches of the pattern in second file :  one
   Replace Value                                   :  Replace sensitiveCase
     */

    @Test
    public void replaceTest10() throws Exception{
        File input = createInputFile3();
        String args1[] = {"replace -i 'can' 'nac' -- file1.txt, file2.txt"};
        Main.main(args1);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(input.getPath());
        assertEquals("Replaced ", expected, actual);

    }

    /*
   Test Case 44 		(Key = 2.2.3.4.3.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest11() throws Exception{
        File input = createInputFile3();
        String args1[] = {"replace -f 'can' 'nac' -- file1.txt, file2.txt"};
        Main.main(args1);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(input.getPath());
        assertEquals("Replaced ", expected, actual);

    }

    /*
   Test Case 45 		(Key = 2.2.3.5.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
   */
    @Test
    public void replaceTest12() throws Exception{
        String args[] ={"replace -f hello HELLO -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "HELLO";

        assertEquals("HELLO", expected);
    }

    /*
   Test Case 47 		(Key = 2.3.2.1.1.3.)
   File                                            :  Not empty
   Options                                         :  -l
   Parameter from                                  :  1length
   Parameter to                                    :  0length
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceTo
     */
    @Test
    public void replaceTest13() throws Exception{
        String args[] ={"replace -l hi hello -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "hi";

        assertEquals("hi", expected);
    }
    
    /*
    Test Case 30 		(Key = 2.2.2.2.2.2.)
    File                                            :  Not empty
    Options                                         :  -f
    Parameter from                                  :  1length
    Parameter to                                    :  1length
    Number of matches of the pattern in second file :  one
    Replace Value                                   :  Replace replaceFrom
    */
     @Test ()
     public void replaceTest14(){
         String args [] = {"replace -f ddddddddDD d  -- file1.txt, file2.txt"};
         Main.main(args);

         String expected = null;

         assertEquals(null, expected);
     }

     /*
    Test Case 27 		(Key = 2.2.2.1.1.2.)
    File                                            :  Not empty
    Options                                         :  -f
    Parameter from                                  :  1length
    Parameter to                                    :  0length
    Number of matches of the pattern in second file :  empty
    Replace Value                                   :  Replace replaceFrom
     */
     @Test ()
     public void replaceTest15(){
         String args [] = {"replace -f ddddddddDDD Boku No Pico  -- file1.txt, file2.txt"};
         Main.main(args);

         String expected = "Boku No Pico is an anime.";

         assertEquals("Boku No Pico is an anime.", expected);
     }

     /*
    Test Case 28 		(Key = 2.2.2.1.2.2.)
    File                                            :  Not empty
    Options                                         :  -f
    Parameter from                                  :  1length
    Parameter to                                    :  0length
    Number of matches of the pattern in second file :  one
    Replace Value                                   :  Replace replaceFrom
    */
     @Test ()
     public void replaceTest16(){
         String args [] = {"replace -f talesofsymphonia TALESOFSYMPHONIA  -- file1.txt, file2.txt"};
         Main.main(args);

         String expected = " is a nintendo game ";

         assertEquals(" is a nintendo Game ", expected);
     }

     /*
    Test Case 23 		(Key = 2.1.3.4.1.1.)
    File                                            :  Not empty
    Options                                         :  -b
    Parameter from                                  :  xlength
    Parameter to                                    :  upper case
    Number of matches of the pattern in second file :  empty
    Replace Value                                   :  Replace backUp
      */
     @Test ()
     public void replaceTest17(){
         String args [] = {"replace -b TalesOfSymphonia TALESOFSYMPHONIA  -- file1.txt, file2.txt"};
         Main.main(args);

         String expected = "TALESOFSYMPHONIA is a NINTENDO game.";

         assertEquals("TALESOFSYMPHONIA is a NINTENDO game.", expected);
     }

    /*
   Test Case 21 		(Key = 2.1.3.3.1.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  xlength
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace backUp
   */
    @Test
    public void replaceTest18(){
        String args[] ={"replace -b ketchup KETCHUP -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "replaced from ketchup to KETCHUP";

        assertEquals("replaced from ketchup to KETCHUP", expected);
    }

 /*
   Test Case 20 		(Key = 2.1.3.2.3.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  xlength
   Parameter to                                    :  1length
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace backUp
 */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest19(){
        String args[] ={"replace -b mustard MUSTARD -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "replaced from mustard to MUSTARD";

        assertEquals("replaced from mustard to MUSTARD", expected);
    }
    
    /*
    Test Case 7  		(Key = 2.1.2.1.1.1.)
    File                                            :  Not empty
    Options                                         :  -b
    Parameter from                                  :  1length
    Parameter to                                    :  0length
    Number of matches of the pattern in second file :  empty
    Replace Value                                   :  Replace backUp
   */
    @Test
    public void replaceTest20() throws Exception{
        File fileInput = createInputFile1();

        String args[] = {"replace -b Hello Howdy file1.txt file2.txt", fileInput.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
               "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(fileInput.getPath());
        assertEquals("replaced",expected, actual);
    }
    
    /*
   Test Case 33 		(Key = 2.2.2.4.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  1length
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
        public void replaceTest21(){
        String args [] = {"replace -f hi Hi -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "noob";
        String actual = "noob";

        assertEquals("no match found!", expected, actual);
    }

    /*
   Test Case 26 		(Key = 2.1.3.5.3.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  xlength
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace backUp
    */
    @Test ()
    public void replaceTest22(){
        String args [] = {"replace -b hi Hi -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "Hi noob";
        String actual = "Hi noob";

        assertEquals("no match found!", expected, actual);
    }

    /*
   Test Case 38 		(Key = 2.2.3.1.3.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  0length
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest23(){
        String args [] = {"replace -f HELLO hello -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "is it me you're looking for";
        String actual = "is it me you're looking for";

        assertEquals("no match found!", expected, actual);
    }

    /*
   Test Case 34 		(Key = 2.2.2.4.2.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  1length
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  one
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest24(){
        String args [] = {"replace -f t a -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "going from up down";
        String actual = "going from up down";

        assertEquals("No match found", expected, actual);
    }

    /*
   Test Case 49 		(Key = 2.3.2.2.1.3.)
   File                                            :  Not empty
   Options                                         :  -l
   Parameter from                                  :  1length
   Parameter to                                    :  1length
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceTo
    */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest25(){
        String args [] = {"replace -f t b -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "the going from up down";
        String actual = "bhe going from up down";

        assertEquals("match found!", expected, actual);
    }

    /*
   Test Case 31 		(Key = 2.2.2.3.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  1length
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
    */
    @Test
    public void replaceTest26(){
        String args [] = {"replace -f t back -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "say going from up down";
        String actual = "say going from up down";

        assertEquals("no match found!", expected, actual);
    }

    /*
   Test Case 32 		(Key = 2.2.2.3.2.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  1length
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  one
   Replace Value                                   :  Replace replaceFrom

     */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest27(){
        String args [] = {"replace -f k back -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "backhe going from up down";
        String actual = "khe going from up down";

        assertEquals("match found!", expected, actual);
    }

    /*
   Test Case 43 		(Key = 2.2.3.4.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest28(){
        String args [] = {"replace -f Winter winter -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "winter time in Baghdad";

        assertEquals("winter time in Baghdad", expected);
    }

    /*
   Test Case 46 		(Key = 2.2.3.5.3.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest29(){
        String args [] = {"replace -f Winter  -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = null;

        assertEquals(null, expected);
    }

    /*
   Test Case 29 		(Key = 2.2.2.2.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  1length
   Parameter to                                    :  1length
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
   */
    @Test ()
    public void replaceTest30(){
        String args [] = {"replace -f cccccccccCC ' '  -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = null;

        assertEquals(null, expected);
    }

    /*
    Test Case 8  		(Key = 2.1.2.1.2.1.)
    File                                            :  Not empty
    Options                                         :  -b
    Parameter from                                  :  1length
    Parameter to                                    :  0length
    Number of matches of the pattern in second file :  one
    Replace Value                                   :  Replace backUp
     */

     @Test
     public void replaceTest31() throws Exception{
         File fileInput = createInputFile1();

         String args[] = {"replace -i Bill William file1.txt file2.txt", fileInput.getPath()};
         Main.main(args);

         String expected = "Howdy Bill,\n" +
                 "This is a test file for the replace utility\n" +
                 "Let's make sure it has at least a few lines\n" +
                 "so that we can create some interesting test cases...\n" +
                 "And let's say \"howdy bill\" again!";

         String actual = getFileContent(fileInput.getPath());
         assertEquals("replaced",expected, actual);
     }
     
     /*
     Test Case 19 		(Key = 2.1.3.2.1.1.)
     File                                            :  Not empty
     Options                                         :  -b
     Parameter from                                  :  xlength
     Parameter to                                    :  1length
     Number of matches of the pattern in second file :  empty
     Replace Value                                   :  Replace backUp
     */
     @Test
     public void replaceTest32(){
         String args[] ={"replace -b mayo MAYO -- file1.txt file2.txt"};
         Main.main(args);
         String expected = "replaced from mayo to MAYO";

         assertEquals("replaced from mayo to MAYO", expected);
     }

     /* 
    
    Test Case 17 		(Key = 2.1.3.1.1.1.)
    File                                            :  Not empty
    Options                                         :  -b
    Parameter from                                  :  xlength
    Parameter to                                    :  0length
    Number of matches of the pattern in second file :  empty
    Replace Value                                   :  Replace backUp
    
      */
     @Test
     public void replaceTest33(){
         String args [] = {"replace -b hello there -- file1.txt, file2.txt"};
         Main.main(args);

         String expected = "hello there";
         String actual = "hello there";

         assertEquals("replaced", expected, actual);
     }

    /*
   Test Case 18 		(Key = 2.1.3.1.3.1.)
   File                                            :  Not empty
   Options                                         :  -b
   Parameter from                                  :  xlength
   Parameter to                                    :  0length
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace backUp
     */
    @Test ()
    public void replaceTest34(){
        String args [] = {"replace -b NIERAUTOMATA nierautomata  -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "nierautomata is a PS4 game.";

        assertEquals("nierautomata is a PS4 game.", expected);
    }

    /*
   Test Case 65 		(Key = 2.3.3.5.1.3.)
   File                                            :  Not empty
   Options                                         :  -l
   Parameter from                                  :  xlength
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceTo
   */
    @Test ()
    public void replaceTest35(){
        String args [] = {"replace -l wutangclan WUTANGCLAN  -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = " ";

        assertEquals("Pattern not found!", expected, expected);
    }

    /*
   Test Case 66 		(Key = 2.3.3.5.3.3.)
   File                                            :  Not empty
   Options                                         :  -l
   Parameter from                                  :  xlength
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  many
   Replace Value                                   :  Replace replaceTo
     */
    @Test ()
    public void replaceTest36(){
        String args [] = {"replace -l wutangclan WUTANGCLAN  -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "WUTANGCLAN is a rap group ";

        assertEquals("Pattern not found!", expected, expected);
    }
    
    /*
   Test Case 72 		(Key = 2.4.2.3.2.4.)
   File                                            :  Not empty
   Options                                         :  -i
   Parameter from                                  :  1length
   Parameter to                                    :  xlength
   Number of matches of the pattern in second file :  one
   Replace Value                                   :  Replace sensitiveCase
     */
    @Test
    public void replaceTest37() throws Exception{
        String args[] = {"replace -i Hello hEllo -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "This is a hEllo test hEllo hEllo";
        String actual = "This is a hEllo test hEllo hEllo";

        assertEquals("Match Found!", expected, actual);

    }

    /*

   Test Case 73 		(Key = 2.4.2.4.1.4.)
   File                                            :  Not empty
   Options                                         :  -i
   Parameter from                                  :  1length
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace sensitiveCase
     */
    @Test
    public void replaceTest38() throws Exception{
        String args[] = {"replace -i", "theCowJumpedOverTheBench", "TheCowJumpedOverTheBench", "-- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        String actual = getFileContent(createInputFile3().getPath());

        assertEquals("Found string!", expected, actual);

    }

    /*
   Test Case 35 		(Key = 2.2.2.5.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  1length
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest39(){
        String args [] = {"replace -f typo typewrite -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = " ";
        String actual = " ";

        assertEquals("No Match!", expected, actual);
    }

    /*
   Test Case 39 		(Key = 2.2.3.2.1.2.)
   File                                            :  Not empty
   Options                                         :  -f
   Parameter from                                  :  xlength
   Parameter to                                    :  1length
   Number of matches of the pattern in second file :  empty
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest40(){
        String args [] = {"replace -f typo typewrite -- file1.txt, file2.txt"};
        Main.main(args);

        String expected = "this typewriter is that typewriter becamse typewrite aslo typewriter";
        String actual = "this typewriter is that typewriter becamse typewrite aslo typewriter";

        assertEquals("Match found!", expected, actual);
    }
    
}