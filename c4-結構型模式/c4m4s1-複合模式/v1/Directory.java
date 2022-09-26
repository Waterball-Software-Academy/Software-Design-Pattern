package v1;

import java.util.ArrayList;
import java.util.List;

import static utils.ValidationUtils.shouldMatch;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Directory {
    private Directory parent;
    private final String name;
    private final List<File> fileChildren = new ArrayList<>();
    private final List<Directory> dirChildren = new ArrayList<>();

    public static Directory root() {
        return new Directory("root");
    }

    public Directory(String name) {
        this.name = shouldMatch("[A-Za-z0-9.\\-_]+", name);
    }

    public void addFile(File file) {
        fileChildren.add(file);
    }

    public void addDirectory(Directory directory) {
        dirChildren.add(directory);
        directory.setParent(this);
    }

    public File getFile(String name) {
        for (File file : fileChildren) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    public Directory getDirectory(String name) {
        for (Directory dir : dirChildren) {
            if (dir.name.equals(name)) {
                return dir;
            }
        }
        return null;
    }

    public long totalBytes() {
        long total = 0;
        for (Directory dir : dirChildren) {
            total += dir.totalBytes();
        }
        for (File file : fileChildren) {
            total += file.bytes();
        }
        return total;
    }

    private void setParent(Directory parent) {
        this.parent = parent;
    }

    public Directory getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }
}
