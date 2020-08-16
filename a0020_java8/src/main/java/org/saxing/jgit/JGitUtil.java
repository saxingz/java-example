package org.saxing.jgit;

import java.io.File;
import java.util.Collection;

import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.HttpConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * jgit
 *
 * @author saxing 2020/8/7 13:18
 */
public class JGitUtil {

    //private static Log log = LogFactory.getLog(JGitUtil.class);

    private JGitUtil() {
    }

    public static Git getGit(String uri, CredentialsProvider credentialsProvider, String localDir) throws Exception {
        Git git = null;
        if (new File(localDir).exists() ) {
            git = Git.open(new File(localDir));
        } else {
            git = Git.cloneRepository().setCredentialsProvider(credentialsProvider).setURI(uri)
                    .setDirectory(new File(localDir)).call();
        }
        //设置一下post内存，否则可能会报错Error writing request body to server
        git.getRepository().getConfig().setInt(HttpConfig.HTTP, null, HttpConfig.POST_BUFFER_KEY, 512*1024*1024);
        return git;
    }

    public static CredentialsProvider getCredentialsProvider(String username, String password) {
        return new UsernamePasswordCredentialsProvider(username, password);
    }

    public static Repository getRepository(Git git) {
        return git.getRepository();
    }

    public static void pull(Git git, CredentialsProvider credentialsProvider) throws Exception {
        git.pull().setRemote("origin").setCredentialsProvider(credentialsProvider).call();
    }

    public static void push(Git git, CredentialsProvider credentialsProvider, String filepattern, String message)
            throws Exception {

        git.add().addFilepattern(filepattern).call();
        git.add().setUpdate(true);
        git.commit().setMessage(message).call();
        git.push().setCredentialsProvider(credentialsProvider).call();

    }

    public static void main(String[] args) throws Exception {
        String uri = "http://gitlab.net/java/audit.git";
        String username = "";
        String password = "";
        CredentialsProvider credentialsProvider = getCredentialsProvider(username, password);

        String localDir = "D:/code/au/0code-branch-detect/audit";
        Git git = getGit(uri, credentialsProvider, localDir);
//        CommitCommand commitCommand = git.commit().setMessage("test commit").setAllowEmpty(true);
//        commitCommand.call();
//        pull(git, credentialsProvider);
//        push(git, credentialsProvider, ".", "提交文件");
        Collection<Ref> call = git.lsRemote().call();
        System.out.println(call);

    }

}