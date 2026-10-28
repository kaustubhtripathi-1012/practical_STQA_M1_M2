package com.college.stqa;
public class Main {
  public static void main(String[] args) throws Exception {
    java.nio.file.Path dir = java.nio.file.Path.of("target", "download-demo");
java.nio.file.Files.createDirectories(dir);
java.nio.file.Path file = dir.resolve("sample-download.txt");
java.nio.file.Files.writeString(file, "STQA file download validation sample");
System.out.println("Practical: File Upload and Download Automation");
System.out.println("Sample file created: " + file.toAbsolutePath());
System.out.println("File exists after download simulation: " + java.nio.file.Files.exists(file));
System.out.println("For real browser upload/download, configure the target page in SeleniumPractical.java.");
  }
}
