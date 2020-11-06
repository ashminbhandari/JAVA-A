import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class CopyTxtFrmDir extends SimpleFileVisitor<Path> {
    private Path sourceDir;
    private Path targetDir;

    public CopyTxtFrmDir(Path sourceDir, Path targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        try {
            //only copy txt files
            if (file.toString().toLowerCase().endsWith(".txt")) {
                Path targetFile = targetDir.resolve(sourceDir.relativize(file));
                Files.copy(file, targetFile);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
        try {
            Path newDir = targetDir.resolve(sourceDir.relativize(dir));
            Files.createDirectory(newDir);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        Path sourceDir = Paths.get(args[0]);
        Path targetDir = Paths.get(args[1]);

        Files.walkFileTree(sourceDir, new CopyTxtFrmDir(sourceDir, targetDir));
    }
}