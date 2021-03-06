package com.kabookja.data.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabookja.data.dao.BooksDAO;
import com.kabookja.data.vo.BooksVO;
import com.kabookja.data.vo.EbooksVO;

@Service("BooksService")
public class BooksServiceImpl implements BooksService {
	@Autowired
	private BooksDAO booksDAO;
	
	@Override
	public ArrayList<BooksVO> books_All(BooksVO bookVO) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return booksDAO.books_All(bookVO);
	}

	@Override
	public ArrayList<BooksVO> books_bestSellerOfTheYear(BooksVO bookVO,int index) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return booksDAO.books_bestSellerOfTheYear(bookVO,index);
	}

	@Override
	public ArrayList<BooksVO> books_bestSellerOfTheMonth(BooksVO bookVO, int index)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return booksDAO.books_bestSellerOfTheMonth(bookVO,index);
	}

	@Override
	public BooksVO books_oneBook(BooksVO bookVO) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return booksDAO.books_oneBook(bookVO);
	}
	
	@Override
	public int books_bookInsert(BooksVO bookVO) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		bookVO.setBookID(bookidCreate(bookVO));
		return booksDAO.books_bookInsert(bookVO);
	}
	
	@Override
	public int books_bookUpdate(BooksVO bookVO) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return booksDAO.books_bookUpdate(bookVO);
	}
	
	@Override
	public int books_bookDelete(BooksVO bookVO) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return booksDAO.books_bookDelete(bookVO);
	}
	//bookid ??????
	public String bookidCreate(BooksVO bookVO) {
		String bookid="";
		
		switch (bookVO.getBookRegion()) {
		case "??????": bookid=bookid+"OV";break;
		case "??????": bookid=bookid+"KO";break;
		}
		bookid=bookid+bookVO.getBookDate().replaceAll("-","");
		switch (bookVO.getBookCategory()) {
		case "??????": bookid=bookid+"NO";break;
		case "SF": bookid=bookid+"SF";break;
		case "?????????": bookid=bookid+"ES";break;
		case "????????????": bookid=bookid+"SD";break;
		case "????????????": bookid=bookid+"PR";break;
		}
		DateFormat fom = new SimpleDateFormat("yyyyMMdd");
		Date date= new Date();
		bookid=bookid+fom.format(date);
		return bookid;	
	}

	@Override
	public ArrayList<EbooksVO> ebooks_All(BooksVO bookVO) throws SQLException {
		// TODO Auto-generated method stub
		return booksDAO.ebooks_All(bookVO);
	}

	@Override
	public EbooksVO ebooks_one(BooksVO bookVO) throws SQLException {
		// TODO Auto-generated method stub
		return booksDAO.ebooks_one(bookVO);
	}

	

	
	
	
}


