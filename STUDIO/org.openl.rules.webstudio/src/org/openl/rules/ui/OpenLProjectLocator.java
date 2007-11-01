/**
 *  OpenL Tablets,  2006
 *  https://sourceforge.net/projects/openl-tablets/ 
 */
package org.openl.rules.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.openl.util.FileTreeIterator;

/**
 * @author snshor
 *
 */
public class OpenLProjectLocator {

	private final String workspace;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		OpenLProjectLocator op = new OpenLProjectLocator("..");
		File[] f = op.listProjects();
		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i].getCanonicalPath() + " - " + op.isRulesProject(f[i]));
			String[] s = op.listPotentialOpenLWrappersClassNames(f[i]);
			for (int j = 0; j < s.length; j++) {
				System.out.println(s[j]);
			}
		}
	}
	
	
	
	public OpenLProjectLocator(String workspace)
	{
        this.workspace = workspace;
    }
	
	
	public OpenLWrapperInfo[] listOpenLProjects() throws IOException 
	{
		Vector v = new Vector();
		File[] f = listProjects();
		for (int i = 0; i < f.length; i++) {
//			System.out.println(f[i].getCanonicalPath() + " - " + isRulesProject(f[i]));
			if (!isRulesProject(f[i]))
				continue;
			OpenLWebProjectInfo wpi = new OpenLWebProjectInfo(workspace, f[i].getName());
			String[] cc = listPotentialOpenLWrappersClassNames(f[i]);
			for (int j = 0; j < cc.length; j++) {
//				System.out.println(s[j]);
				v.add(new OpenLWrapperInfo(cc[j], wpi));
			}
		}
		
		return (OpenLWrapperInfo[]) v.toArray(new OpenLWrapperInfo[0]);
	}
	
	
	/**
	 * @param project
	 * @return
	 * @throws IOException 
	 */
	public String[] listPotentialOpenLWrappersClassNames(File project) throws IOException {
		
		String srcroot = "gen";
		File searchDir = new File(project.getCanonicalPath(), srcroot);
		FileTreeIterator fti = new FileTreeIterator(searchDir, 0);
		Vector v = new Vector();
		for (; fti.hasNext();) {
			File f = (File) fti.next();
			if (f.getName().endsWith(".java"))
				v.add(javaClassName(f, searchDir.getCanonicalPath()));
		}
		
		
		
		
		return (String[])v.toArray(new String[0]);
	}




	/**
	 * @param f
	 * @return
	 */
	private String javaClassName(File f, String srcroot) {
		
		String path = f.getPath();
		int inc = 1;
		if (srcroot.endsWith(File.separator))
			inc = 0;
		
		String jpath = path.substring(srcroot.length() + inc, path.length() - 5);
		return jpath.replace(File.separatorChar, '.');
		
		
	}




	File[] listProjects()
	{
		
		File wsfolder = new File(workspace);
		return wsfolder.listFiles();
		
	}
	
	
	boolean isRulesProject(File f) throws IOException
	{
		if (!f.exists() || !f.isDirectory())
			return false;
		
		if (!containsFile(f, ".project", false))
			return false;

		if (!containsFileText(f, ".project", "openlbuilder"))
			return false;
		return true;
	}


	
	
	


	private boolean containsFileText(File dir, String fname, String content) throws IOException 
	{
		File f = new File(dir.getCanonicalPath(), fname);
		
		FileReader fr = new FileReader(f);
		
		BufferedReader br = new BufferedReader(fr);
		
		try
		{
			while(true)
			{	
				String line =	br.readLine();
				if (line == null)
					break;
				if (line.indexOf(content) >=0)
					return true;
			}
			return false;
			
		}
		finally
		{
			br.close();
			fr.close();
		}
		
	}




	private boolean containsFile(File f, String fname, boolean isDir) throws IOException {
		File ff = new File(f.getCanonicalPath(), fname);
		return ff.exists() && ff.isDirectory() == isDir;
		
	}



	/**
	 * @return Returns the workspace.
	 */
	public String getWorkspace() {
		return workspace;
	}
}
