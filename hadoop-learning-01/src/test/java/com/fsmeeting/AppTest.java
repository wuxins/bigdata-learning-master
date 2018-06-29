package com.fsmeeting;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private FileSystem fileSystem = null;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        //conf.set("dfs.replication", "0");
        //System.setProperty("HADOOP_USER_NAME","root");
        //conf.set("fs.defaultFS","hdfs://node-1:9000");
        //fileSystem = FileSystem.get(new URI("hdfs://node-1.example.com:9000/"), conf, "root");
        fileSystem = FileSystem.get(new URI("hdfs://node-1:9000/"), conf, "root");
    }

    @Test
    public void testExists() throws IOException {
        Path path = new Path("/fsmeeting/pome.txt");
        System.out.println(fileSystem.exists(path));
    }

    @Test
    public void testAppend() throws IOException {
      /*  Path path = new Path("/test01.md");
        fileSystem.append(path);
        System.out.println();*/
    }

    @Test
    public void testCreate() throws IOException {
        Path path = new Path("/1.txt");
        FSDataOutputStream out = fileSystem.create(path, true);
        out.write("hello hadoop".getBytes());
    }

    @Test
    public void testCreateNewFile() throws IOException {
        Path path = new Path("2.txt");
        fileSystem.createNewFile(path);
    }

    /**
     * hdfs 2 local file system
     */
    @Test
    public void testDownload() throws IOException {
        InputStream in = fileSystem.open(new Path("/1.txt"));
        OutputStream out = new FileOutputStream("x://test//tmp088.txt");
        IOUtils.copyBytes(in, out, 1024, true);
    }

    /**
     * hdfs 2 local file system
     */
    @Test
    public void testDownload1() throws IOException {

        fileSystem.copyToLocalFile(false, new Path("/up.txt"), new Path("x://test//tmp099.txt"), true);
    }

    @Test
    public void testUpload() throws IOException {

        fileSystem.copyFromLocalFile(new Path("e://postClientData.jmx"), new Path("/"));
    }

    @Test
    public void testDelete() throws IOException {
        Path path = new Path("/up.txt");
        fileSystem.delete(path);
    }

    /**
     * 文件状态
     *
     * @throws IOException
     */
    @Test
    public void testGetFileStatus() throws IOException {
        Path path = new Path("/up.txt");
        FileStatus status = fileSystem.getFileStatus(path);
        System.out.println(status.getModificationTime());
    }

    /**
     * 文件状态，可以获得文件夹信息
     *
     * @throws IOException
     */
    @Test
    public void testListFile() throws IOException {
        Path path = new Path("/");
        FileStatus[] status = fileSystem.listStatus(path);
        for (FileStatus fileStatus : status) {
            System.out.println(fileStatus.getPath());
        }
    }

    /**
     * 创建文件夹
     *
     * @throws IOException
     */
    @Test
    public void testMkdirs() throws IOException {
        Path path = new Path("/test");
        fileSystem.mkdirs(path);
    }

    /**
     * 移动本地文件到hdfs
     *
     * @throws IOException
     */
    @Test
    public void testMoveFromLocalFile() throws IOException {
        Path path = new Path("/test");
        fileSystem.moveFromLocalFile(new Path("C:\\Users\\liuyc\\Desktop\\setenv.sh"), path);
    }

    /**
     * 重命名
     *
     * @throws IOException
     */
    @Test
    public void testRename() throws IOException {
        Path path = new Path("/test01.md");
        Path dest = new Path("/1.txt");
        fileSystem.rename(path, dest);
    }

    @After
    public void destroy() throws IOException {
        fileSystem.close();
    }
}
