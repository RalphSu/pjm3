package org.pjm.poi;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final long MAX_SIZE = 1000 * 50000;
	private static final String CONTENT_TYPE_UNACCEPTABLE = "{ERROR: 'Content type unacceptable. "
		+ " Only xls file can be uploaded'}";
	private static final String SIZE_UNACCEPTABLE =  "{ERROR: 'File size must be " + MAX_SIZE + " bytes or less'}";
	private static final String SUCCESS_MESSAGE =  "{OK: 'File upload succeeded'}";
	private static final Logger log = Logger.getLogger(FileUploadServlet.class.getName());
	private String uploadPath;
	


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
//		uploadPath=this.getServletContext().getRealPath(LifecycleListener.UPLOADPATH);
//		FileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		List items = null;
//		JSONObject json = new JSONObject();
//
//		try 
//		{
//			json.put("success", true);
//			json.put("error", "uploaded successfully");
//			json.put("code", "232");
//			items = upload.parseRequest(request);
//		}
//		catch (Exception e) 
//		{
//			log.warning("Upload error" + e.getMessage());
//		}
//		
//		Iterator it = items.iterator();
//		while (it.hasNext()) 
//		{
//			FileItem item = (FileItem) it.next();
//			if(!item.isFormField()) {
//				try
//				{
//					processFile(item);
//				}
//				catch(Exception ex)
//				{
//					log.log(Level.WARNING, "Upload file process error" , ex);
//					System.out.println("Upload file process error"+ex);
//					try {
//						json.put("success", false);
//						json.put("error", ex.getMessage());
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						log.warning("error"+e);
//					}
//				}
//			}
//	
//		}
//		Writer w = new OutputStreamWriter(response.getOutputStream());
//		w.write(json.toString());
//		w.close();

	}
//
//	private synchronized void processFile(FileItem item) throws Exception
//	{
//		if (!isSizeAcceptable(item)){
//			throw new RuntimeException(SIZE_UNACCEPTABLE) ;
//		}
//			
//		log.info("upload file: " + item.getName());
//		String clientFilename = StringUtils.parseFileName(item.getName());
//		String filepath = uploadPath+"\\"+item.getFieldName();
//		File file = new File(filepath);
//		if(!file.exists())file.mkdirs();
//		file = new File(filepath + File.separator + clientFilename);
//		item.write(file);
//
//	}
//	
//	private boolean isSizeAcceptable(FileItem item) 
//	{
//		return item.getSize() <= MAX_SIZE;
//	}
//	
//	private boolean isContentTypeAcceptable(FileItem item) 
//	{
//		String fileName = item.getName();
//		//if(!fileName.toLowerCase().endsWith("wav"))return false;
//		return true;
//	}
//	

}
