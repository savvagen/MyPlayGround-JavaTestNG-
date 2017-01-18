package Utilities.Listeners;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestResults {

    int passed;
    int failed;
    int skipped;

    public int getPassed(){
        return this.passed;
    }

    public int getFailed(){
        return this.failed;
    }

    public int getSkipped(){
        return this.skipped;
    }

    public int getTotalTests(){
        return (this.passed + this.failed + this.skipped);
    }


    public void incrementPassed(){
        this.passed++;
    }

    public void incrementSkipped(){
        this.skipped++;
    }

    public void incrementFailed(){
        this.failed++;
    }

    public void writeToSheet() throws IOException{
        File report = new File("MyTestReport.txt");
        try(FileWriter fw = new FileWriter(report);
            BufferedWriter bWriter = new BufferedWriter(fw)) {
            bWriter.write("PASSED: " + this.passed + "\n");
            bWriter.write("FAILED: " + this.failed + "\n");
            bWriter.write("SKIPPED: " + this.skipped + "\n");
            bWriter.write("TOTAL TESTS: " + this.getTotalTests() + "  ");

        }
    }



}
