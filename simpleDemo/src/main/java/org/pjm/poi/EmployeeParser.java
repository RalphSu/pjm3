package org.pjm.poi;

import java.util.logging.Logger;


/**
 * this class can be more flexiable . for example we can add 
 * validate listener in .Then we don't need validate file content
 * by hard code
 * 
 * @author hjiang
 *
 */
public class EmployeeParser {

	private static final Logger log = Logger.getLogger(EmployeeParser.class
			.getName());
	
	private String[] formats;

//	private BasicManagementDAO bmDAO;
	
	private static final short NAMEINDEX=0;
	private static final short PERSONALIDINDEX=1;
	private static final short EMPLOYEENOINDEX=2;
	private static final short MALEINDEX=3;
	private static final short ONBOARDDATEINDEX=4;
	private static final short PERMITTYPEINDEX=5;
	private static final short PHONEINDEX=6;
	private static final short ADDRESSINDEX=7;
	private static final short ZIPCODEINDEX=8;
	private static final short HOUSEFUNDINDEX=9;
	private static final short HUKOUINDEX=10;
	private static final short MARRIEDINDEX=11;
	private static final short KIDNAMEINDEX=12;
	private static final short KIDBIRTHINDEX=13;
	private static final short KIDMALEINDEX=14;
	private static final short DOCUMENTINDEX=15;
	private static final short ACCOUNTINDEX=16;
	private static final short ACCOUNTNAMEINDEX=17;
	private static final short BANKNAMEINDEX=18;
	private static final short PERMITLOCATIONINDEX=19;
	private static final short PERMITEXPIREINDEX=20;
	private static final short BIRTHDAYINDEX=21;
	private static final short MAILINDEX=22;
	private static final short LEVELINDEX=23;
	private static final short PARTYMEMBERINDEX=24;
	
	public EmployeeParser(/*BasicManagementDAO bmDAO*/) {
//		this.bmDAO = bmDAO;
		formats= new String[25];
//		formats[NAMEINDEX] = "姓名";
//		formats[PERSONALIDINDEX] = "身份证号";
//		formats[EMPLOYEENOINDEX] = "员工编号";
//		formats[MALEINDEX] = "性别";
//		formats[ONBOARDDATEINDEX] = "入职日期";
//		formats[PERMITTYPEINDEX] = "居住类型";
//		formats[PHONEINDEX] = "电话好吗";
//		formats[ADDRESSINDEX] = "家庭地址";
//		formats[ZIPCODEINDEX] = "邮编";
//		formats[HOUSEFUNDINDEX] = "公积金账号";
//		formats[HUKOUINDEX] = "户口所在地";
//		formats[MARRIEDINDEX] = "婚否";
//		formats[KIDNAMEINDEX] = "儿女姓名";
//		formats[KIDBIRTHINDEX] = "儿女出生日期";
//		formats[KIDMALEINDEX] = "儿女性别";
//		formats[DOCUMENTINDEX] = "档案所在地";
//		formats[ACCOUNTINDEX] = "工资卡账号";
//		formats[ACCOUNTNAMEINDEX] = "账户名称";
//		formats[BANKNAMEINDEX] = "银行支行名称";
//		formats[PERMITLOCATIONINDEX] = "居住证办理地点";
//		formats[PERMITEXPIREINDEX] = "居住证到期时间";
//		formats[BIRTHDAYINDEX] = "出生日期";
//		formats[MAILINDEX] = "邮箱";
//		formats[LEVELINDEX] = "级别";
//		formats[PARTYMEMBERINDEX] = "是否党员";
	}
	

//
//	public List<EmployeeInfoDbo> getEmployees(FileInputStream fileIn,CompanyInfoDbo company,boolean isNew) throws Exception{
//		List<EmployeeInfoDbo>resu = new ArrayList<EmployeeInfoDbo>();
//		POIFSFileSystem fs = new POIFSFileSystem(fileIn);
//		HSSFWorkbook wb = new HSSFWorkbook(fs);
//		HSSFSheet sheet = wb.getSheetAt(0);
//		if (sheet == null)
//			throw new Exception("Format is not correct");
//		HSSFRow headrow = sheet.getRow(sheet.getFirstRowNum());
//		if (headrow == null)
//			throw new Exception("Format is not correct");
//		log.info("parsing file head");
//		
//		// verify head
//		verifyNewEmployeeFileHead(company, headrow, formats);
//		// except head
//		// begin parsing data area
//		log.info("parsing file head success");
//		java.text.DecimalFormat   formatter   =   
//			new   java.text.DecimalFormat("########");
//
//		
//		for (int m = sheet.getFirstRowNum() + 1; m <= sheet.getLastRowNum(); m++) {
//			HSSFRow row = sheet.getRow(m);
//		    if(row==null) continue;
//			EmployeeInfoDbo employee = new EmployeeInfoDbo();
//		    HSSFCell nameCell = row.getCell((short)NAMEINDEX);
//		    if(nameCell==null||nameCell.getRichStringCellValue().getString()==null
//		    		||nameCell.getRichStringCellValue().getString().length()==0) continue;
//		    
//		    employee.setEmployeeName(nameCell.getRichStringCellValue().getString());
//		    HSSFCell idCell = row.getCell((short)PERSONALIDINDEX);
//		    if(idCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
//		    	employee.setPersonalID(idCell.getRichStringCellValue().getString().trim());
//		    	employee.setPersonalID(employee.getPersonalID().toLowerCase());
//		    }
//		    else if (idCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
//		    	   String   personalID   =   formatter.format(idCell.getNumericCellValue());   
//				   employee.setPersonalID(personalID.toLowerCase());
//		    }else
//		    	throw new Exception("In "+m+" row the personal id value is not correct");
//		 
//		    HSSFCell noCell = row.getCell(EMPLOYEENOINDEX);
//		    if (noCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
//		    	   int no =new Double(noCell.getNumericCellValue()).intValue() ;   
//		    	   if(no!=0)	
//		    		   employee.setEmployeeNO(String.valueOf(no));
//		    }else   if(noCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
//		    	String no = noCell.getRichStringCellValue().getString();
//		    	if(no!=null&&no.length()>0){
//		    		employee.setEmployeeNO(no);
//		    	}
//		    }
//		    	
//		    
//		    HSSFCell sexCell = row.getCell((short)MALEINDEX);
//		    String sex = sexCell.getRichStringCellValue().getString();
//		    sex =sex.trim();
//		    if(sex==null||(!sex.equalsIgnoreCase(ServerDefinitions.MALE)&&!sex.equalsIgnoreCase(ServerDefinitions.FEMALE))){
//		    	throw new Exception("In "+m+" row the sexual value is not correct.It should be male or female");
//		    }
//		    employee.setSex(sex);
//		    HSSFCell dateCell = row.getCell((short)ONBOARDDATEINDEX);
//		    if(dateCell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
//		    	throw new Exception("In "+m+" row the OnboardDate is empty ");
//		    if(dateCell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
//		    	throw new Exception("In "+m+" row the OnboardDate format is not data format");
//		    if(!HSSFDateUtil.isCellDateFormatted(dateCell))
//		    	throw new Exception("In "+m+" row the OnboardDate format is not data format");
//		    employee.setOnboardDate(dateCell.getDateCellValue());
//		    
//		    
//		    
//		    HSSFCell typeCell = row.getCell((short)PERMITTYPEINDEX);
//		    String type = typeCell.getRichStringCellValue().getString();
//		    
//		    if(type==null){
//		    	throw new Exception("In"+m+"row the residencetype is empty ");
//		    }
//		    type = type.trim();
//		    if(type.equals("上海户�?")) type=ServerDefinitions.LOCALPERMIT;
//		    else if (type.equals("居�?�?")) type = ServerDefinitions.CARDPERMIT;
//		    else if (type.equals("外地人员")) type = ServerDefinitions.OUTLANDER;
//		    else if (type.equals("外�?人士")) type = ServerDefinitions.FOREIGNER;
//		    if((!type.equalsIgnoreCase(ServerDefinitions.LOCALPERMIT))&&!type.equalsIgnoreCase(ServerDefinitions.CARDPERMIT)
//		    		&&!type.equalsIgnoreCase(ServerDefinitions.OUTLANDER)&&!type.equalsIgnoreCase(ServerDefinitions.FOREIGNER)){
//		    	throw new Exception(
//						"In "
//								+ m
//								+ " row the residencetype value is not correct.It should be local(or 上海户�?) or residencecard(居�?�?) or " +
//										"outlander(外地人员) or foreigner(外�?人士)"); 
//		    }
//		    employee.setPermitType(type);
//		    
//		    HSSFCell phoneCell = row.getCell((short)PHONEINDEX);
//		    if(phoneCell.getCellType()==HSSFCell.CELL_TYPE_STRING)
//		    	employee.setPhoneNumber(phoneCell.getRichStringCellValue().getString());
//		    else if (phoneCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
//		    	   String   phoneNumber   =   formatter.format(phoneCell.getNumericCellValue());   
//				   employee.setPhoneNumber(phoneNumber);
//		    }else
//		    	throw new Exception("In "+m+" row the phoneNumber id value is not correct");
//		    
//		    HSSFCell addressCell = row.getCell((short)ADDRESSINDEX);
//		    if(addressCell!=null){
//		    	String address = addressCell.getRichStringCellValue().getString();
//			    if(address!=null)
//			    	employee.setHomeaddress(address);	
//		    }
//		    
//		    
//		    HSSFCell postCodeCell= row.getCell((short)ZIPCODEINDEX);
//		    if(postCodeCell!=null){
//		    	if(postCodeCell.getCellType()==HSSFCell.CELL_TYPE_STRING)
//			    	employee.setPostcode(postCodeCell.getRichStringCellValue().getString());
//			    else if (postCodeCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
//			    	   String   postCode   =   formatter.format(postCodeCell.getNumericCellValue());   
//					   employee.setPostcode(postCode);
//			    }
//		    }
//		    
//		    
//		    HSSFCell houseaccountCell= row.getCell((short)HOUSEFUNDINDEX);
//		    if(houseaccountCell!=null){
//		    	if(houseaccountCell.getCellType()==HSSFCell.CELL_TYPE_STRING)
//			    	employee.setHousefundAccount(houseaccountCell.getRichStringCellValue().getString());
//			    else if (houseaccountCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
//			    	   String   houseaccount   =   formatter.format(houseaccountCell.getNumericCellValue());   
//					   employee.setHousefundAccount(houseaccount);
//			    }
//		    }
//		    
//		    HSSFCell hukouCell = row.getCell((short)HUKOUINDEX);
//		    if(hukouCell!=null){
//		    	String  hukou= hukouCell.getRichStringCellValue().getString();
//			   	employee.setHukou(hukou);	
//		    }
//		    
//		    HSSFCell marriedCell = row.getCell((short)MARRIEDINDEX);
//		    if(marriedCell!=null){
//		    	if(marriedCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN)
//		    		employee.setMarried(marriedCell.getBooleanCellValue());
//		    	else if(marriedCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
//		    		String married = marriedCell.getRichStringCellValue().getString();
//		    		married = married.trim();
//		    		if (married.equals("是") || married.equalsIgnoreCase("yes")
//							|| married.equalsIgnoreCase("married")
//							|| married.equalsIgnoreCase("已婚"))
//		    			employee.setMarried(true);
//		    		else
//		    			employee.setMarried(false);
//		    	}
//		    }
//		  
//		    HSSFCell childNameCell = row.getCell((short)KIDNAMEINDEX);
//		    if(childNameCell!=null&&childNameCell.getRichStringCellValue().getString()!=null&&
//		    		childNameCell.getRichStringCellValue().getString().length()>0){
//		    	String kidsNames = childNameCell.getRichStringCellValue().getString();
//		    	 List<KidsInfoDbo> kidsInfos = new ArrayList<KidsInfoDbo>();
//		    	String[] tmpNames=kidsNames.split(";");
//		    	String[] tmpBornDate=null;
//		    	String[] tmpGenders=null;;
//		    	
//		    	HSSFCell childBornDateCell = row.getCell((short)KIDBIRTHINDEX);
//		    	if(childBornDateCell!=null&&childBornDateCell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
//		    		throw new Exception("In "+m+" row the Child Born Date is not Text type");
//		    	if(childBornDateCell!=null&&childBornDateCell.getRichStringCellValue().getString()!=null&&
//		    			childBornDateCell.getRichStringCellValue().getString().length()>0){
//		    		tmpBornDate = childBornDateCell.getRichStringCellValue().getString().split(";");
//		    	}
//		    	if(tmpBornDate==null||tmpBornDate.length!=tmpNames.length){
//		    		throw new Exception("In "+m+" row the Child Born Date is not adapt with Child Names");
//		    	}
//		    	
//		    	
//		    	HSSFCell childSexCell = row.getCell((short)KIDMALEINDEX);
//		    	if(childSexCell!=null&&childSexCell.getRichStringCellValue().getString()!=null&&
//		    			childSexCell.getRichStringCellValue().getString().length()>0){
//		    		   String childsex = childSexCell.getRichStringCellValue().getString();
//		    		   tmpGenders = childsex.split(";");
//		    	}
//		    	if(tmpGenders==null||tmpGenders.length!=tmpNames.length){
//		    		throw new Exception("In "+m+" row the Child Gender is not adapt with Child Names");
//		    	}
//		    	SimpleDateFormat bornformatter = new SimpleDateFormat("yyyyMMdd");
//			    for(int kid=0;kid<tmpNames.length;kid++){
//			    	KidsInfoDbo kidInfo= new KidsInfoDbo();
//			    	tmpNames[kid] = tmpNames[kid].trim();
//			    	kidInfo.setName(tmpNames[kid]);
//			    	try{
//			    		String kidBornDate = tmpBornDate[kid];	
//			    	    Date kidDate = bornformatter.parse(kidBornDate);
//			    	    kidInfo.setBornDate(kidDate);
//			    	}catch (ParseException pe) {
//			    		throw new Exception("In "+m+" row the Child Born Date should be like yyyyMMdd:19800530");
//					}
//			    	tmpGenders[kid] = tmpGenders[kid].trim();
//			    	if(!tmpGenders[kid].equalsIgnoreCase("male")&&!tmpGenders[kid].equalsIgnoreCase("female")){
//				    	throw new Exception("In "+m+" row the kid gender value is not correct.It should be male or female");
//				    }
//			    	kidInfo.setGender(tmpGenders[kid]);
//			    	kidInfo.setEmployee(employee);
//			    	kidsInfos.add(kidInfo);
//			    }
//		    	employee.setKids(kidsInfos);
//		    }
//		    
//		    HSSFCell documentCell = row.getCell((short)DOCUMENTINDEX);
//		    if(documentCell!=null){
//		    	employee.setDocumentLocation(documentCell.getRichStringCellValue().getString());
//		    }
//		    
//		    HSSFCell  bankAccountCell = row.getCell((short)ACCOUNTINDEX);
//		    if(bankAccountCell!=null){
//		    	employee.setBankAccount(bankAccountCell.getRichStringCellValue().getString());
//		    }
//		    HSSFCell accountNameCell = row.getCell((short)ACCOUNTNAMEINDEX);
//		    if(accountNameCell!=null&&!type.equalsIgnoreCase(ServerDefinitions.FOREIGNER)){
//		    	employee.setAccountName(accountNameCell.getRichStringCellValue().getString());
//		    	if(!employee.getAccountName().equals(employee.getEmployeeName()))
//		    		throw new Exception("In "+m+" row the bank account name is not correct.It should as same as employee's name");
//		    }
//		    
//		    HSSFCell bankNameCell  = row.getCell((short)BANKNAMEINDEX);
//		    if(bankNameCell!=null){
//		    	employee.setBankName(bankNameCell.getRichStringCellValue().getString());
//		    }
//		    
//		    HSSFCell residenceCardLocationCell = row.getCell((short)PERMITLOCATIONINDEX);
//		    if(residenceCardLocationCell==null&&employee.getPermitType().equals(ServerDefinitions.CARDPERMIT))
//		    	throw new Exception(
//						"In "
//								+ m
//								+ " row the residence card location is empty when this employee is residence permit");
//		    if(residenceCardLocationCell!=null){
//		    	employee.setResidenceCardLocation(residenceCardLocationCell
//						.getRichStringCellValue().getString());
//		    }
//		    
//		    HSSFCell residenceExpireDateCell = row.getCell((short)PERMITEXPIREINDEX);
//		    if(residenceExpireDateCell==null&&employee.getPermitType().equals(ServerDefinitions.CARDPERMIT))
//		    	throw new Exception("In "+m+" row the residence card expire date is empty");
//		    if(residenceExpireDateCell!=null&&residenceExpireDateCell.getCellType()==HSSFCell.CELL_TYPE_BLANK
//		    		&&employee.getPermitType().equals(ServerDefinitions.CARDPERMIT))
//		    	throw new Exception("In "+m+" row the residence card expire date is empty");
//		    if(residenceExpireDateCell!=null&&employee.getPermitType().equals(ServerDefinitions.CARDPERMIT)){
//		    	if(residenceExpireDateCell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
//			    	throw new Exception("In "+m+" row the residence card expire date is not date format");
//			    if(!HSSFDateUtil.isCellDateFormatted(residenceExpireDateCell))
//			    	throw new Exception("In "+m+" row residence card expire date format is not date format");
//			    employee.setResidenceExpireDate(residenceExpireDateCell.getDateCellValue());
//		    }
//		    
//		    HSSFCell birthDayCell = row.getCell((short)BIRTHDAYINDEX);
//		    if(birthDayCell==null||birthDayCell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
//		    	throw new Exception("In "+m+" row the birthday is empty"); 
//		    if(birthDayCell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
//		    	throw new Exception("In "+m+" row the birthday is not date format"); 
//		    if(!HSSFDateUtil.isCellDateFormatted(birthDayCell))
//		    	throw new Exception("In "+m+" row birthday format is not date format");
//		    employee.setBirthDay(birthDayCell.getDateCellValue());
//		    
//		    HSSFCell mailCell = row.getCell((short)MAILINDEX);
//		    if(mailCell!=null&&mailCell.getRichStringCellValue().getString()!=null
//		    		&&mailCell.getRichStringCellValue().getString().length()>0){
//		    	employee.setEmailAddress(mailCell.getRichStringCellValue()
//						.getString());
//		    }
//		   
//	
//
//		    
//		    HSSFCell partyMemberCell = row.getCell((short)PARTYMEMBERINDEX);
//		    if(partyMemberCell!=null){
//		    	if(partyMemberCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN)
//		    		employee.setPartyMember(partyMemberCell.getBooleanCellValue());
//		    	else if(partyMemberCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
//		    		String partyMember = partyMemberCell.getRichStringCellValue().getString();
//		    		partyMember=partyMember.trim();
//		    		if (partyMember.equals("是") || partyMember.equalsIgnoreCase("yes")
//							|| partyMember.equalsIgnoreCase("true"))
//		    			employee.setPartyMember(true);
//		    		else
//		    			employee.setPartyMember(false);
//		    	}
//		    }
//		    
//		    resu.add(employee);
//		    
//		}
//		return resu;
//	}
//	
//	private void verifyNewEmployeeFileHead(CompanyInfoDbo company, HSSFRow row,
//			String[] formats) throws Exception {
//		for (int m=0;m<formats.length;m++) {
//			HSSFCell cell = row.getCell((short)m);
//			if (cell == null)
//				throw new Exception(
//						"Your File Head is not correct,The value is null which should be ["
//								+ formats[m] + "] " + " in "
//								+ (m+1) + " column");
//			if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING)
//				throw new Exception(
//						"Your File Head is not correct,The value is unknown which should be ["
//								+ formats[m] + "] " + " in "
//								+ (m+1) + " column");
//			if (!cell.getRichStringCellValue().getString().equalsIgnoreCase(
//					formats[m]))
//				throw new Exception(
//						"Your File Head is not correct,The value is ["
//								+ cell.getRichStringCellValue().getString()
//								+ "] which should be ["
//								+ formats[m] + "] " + " in "
//								+ (m+1) + " column");
//		}
//	}

}


