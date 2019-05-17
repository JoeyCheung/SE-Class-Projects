package edu.qc.seclass.replace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Replace {

	String c;
    String fs;
    String cts;
    String[] cb;
    ArrayList<String> files = new ArrayList<String>();
    
    boolean bf;
    boolean pdd;
    boolean pfs;
    boolean fof;
    boolean lof;
    boolean cif;

    public Replace(String c) throws IOException {
        cif = false;
        bf = false;
        pdd = false;
        pfs = false;
        this.c = c;
        fs = null;
        cts = null;
        fof = false;
        lof = false;
        
        pc(this.c);

    }
    
    private void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*");
    }

    public void ec() throws IOException {

        if (bf == true) {
            bf();
        }

        for (int j = 0; j < files.size(); j++) {

            File ftbm = new File(files.get(j));
            BufferedReader r = new BufferedReader(new FileReader(files.get(j)));
            String fc = "";
            String l = r.readLine();
            while (l != null) {
                fc = fc + l + System.lineSeparator();
                l = r.readLine();
            }

            if (cif == false && fof == true && lof == false) {

                String newfc = fc.replaceFirst(fs, cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }

            if (cif == true && fof == true && lof == false) {

                String newfc = fc.replaceFirst("(?s)" + fs + "(?!.*?" + fs + ")", cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }
            if (fof == true && lof == true && cif == true) {
                String fc2 = ci(fc);

                String newfc = fc2.replaceFirst(fs, cts);
                newfc = fc.replaceFirst("(?s)" + fs + "(?!.*?" + fs + ")", cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }

            if (fof == false && lof == false && cif == false) {
                String newfc = fc.replaceAll(fs, cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }

            if (fof == false && lof == true && cif == false) {
                String newfc = fc.replaceFirst("(?s)" + fs + "(?!.*?" + fs + ")", cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }

            if (fof == false && lof == true && cif == true) {
                String fc2 = ci(fc);

                String newfc = fc2.replaceFirst(fs, cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }
            
            if (fof == true && lof == true && cif == false) {

                String newfc = fc.replaceFirst(fs, cts);
                newfc = fc.replaceFirst("(?s)" + fs + "(?!.*?" + fs + ")", cts);


                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();

            }
            if (fof == false && lof == false && cif == true) {
                String fc2 = ci(fc);

                String newfc = fc2.replaceAll(fs, cts);

                FileWriter w = new FileWriter(ftbm);
                w.write(newfc);

                r.close();
                w.close();
            }
        }
    }
    
    public String ci(String fc) {
        String[] t = fc.split(" ");
        String o = "";
        for (int j = 0; j < t.length; j++) {
            String t2 = t[j];
            if (t2.equalsIgnoreCase(fs)) {
                t2 = t2.toLowerCase();
            }
            o += t2 + " ";
        }
        return o;
    }	

    public void bf() {

        for (int j = 0; j < files.size(); j++) {
            Path t = Paths.get(files.get(j) + ".bck");
            Path s = Paths.get(files.get(j));
            try {
                Files.copy(s, t);
            } catch (IOException e1) {
                System.out.println("Error backup " + files.get(j) + ".bck exists");
            }
        }
    }
    
    public void pc(String c) throws IOException {
        cb = c.split("\\s+");

        if (!cb[0].equalsIgnoreCase("replace")) {
            usage();
        }

        for (int j = 1; j < cb.length; j++) {
        	
            if ((pfs == false) && (pdd == false) && (!cb[j].equalsIgnoreCase("-l")) && (!cb[j].equalsIgnoreCase("-f")) &&(!cb[j].equalsIgnoreCase("-i"))  && (!cb[j].equalsIgnoreCase("-b"))) {
                fs = cb[j];
                pfs = true;
            }
            if ((pfs == true) && (pdd == false) && (!cb[j].equalsIgnoreCase("-f")) && (!cb[j].equalsIgnoreCase("-l")) && (!cb[j].equalsIgnoreCase("-i"))  && (!cb[j].equalsIgnoreCase("-b"))) {
                cts = cb[j];   
            }
                
            if (cb[j].equalsIgnoreCase("-b")) {
                cif = true;
            }

            if (cb[j].equalsIgnoreCase("-l")) {
                lof = true;
            }

            if (cb[j].equalsIgnoreCase("-i")) {
                fof = true;
            }

            if (cb[j].equalsIgnoreCase("-f")) {
                bf = true;
            }

            if (pdd == true) {
                files.add(cb[j]);
            }

            if (cb[j].equalsIgnoreCase("--")) {
                pdd = true;
            }

            if (j == (cb.length - 1) && pdd == false) {
                usage();
            }

        }
        if (fs == null || cts == null) {
            usage();
        }
        cfe();
    }
    
    public void cfe() throws IOException {
        for (int j = 0; j < files.size(); j++) {
            File f = new File(files.get(j));
            if (!f.isFile()) {
                usage();
            }
        }
        ec();
    }
}