package com.netease.shop.servicei.mpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netease.shop.dao.Contentdao;
import com.netease.shop.meta.Productlist;
import com.netease.shop.meta.TrxInfobean;
import com.netease.shop.service.Trxinfo;

@Service
public class AddTrxinfo implements Trxinfo {
    private JdbcTemplate jdbcTemplate;
    
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
    //添加库存
	@Override
	@Transactional(propagation= Propagation.REQUIRED) 
	public void addTrx(List<Productlist> products,int personId) {
		 int pId = personId;
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		Contentdao dao = context.getBean("contentdao",Contentdao.class); 

	    final List<TrxInfobean> trxlist = new ArrayList<TrxInfobean>();
		
		for(Productlist p: products){
			int contentId = p.getId();//商品Id
			long price = dao.GetpriceById(contentId);//商品价格
			int num  = p.getNumber(); //购买数量
			dao.updateProductNum(num, contentId); 
			Date date = new Date();//得到系统时间
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
			String goodsC_date =sdf.format(date);			
			TrxInfobean trx = new TrxInfobean();
			trx.setContentId(contentId);
			trx.setpId(pId);
			trx.setPrice(price);
			trx.setGoodsC_date(goodsC_date);
			trx.setNum(num);
			trxlist.add(trx);		
		}
		String sql ="insert into trx values (null,?,?,?,?,?)";
		//通过内部类创建批处理对象
			BatchPreparedStatementSetter bts=new BatchPreparedStatementSetter() {
			
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					 ps.setInt(1, trxlist.get(i).getContentId());
					 ps.setInt(2, trxlist.get(i).getpId());
					 ps.setLong(3, trxlist.get(i).getPrice());
					 ps.setString(4, trxlist.get(i).getGoodsC_date());
					 ps.setInt(5, trxlist.get(i).getNum());
				
					
				}
			
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return trxlist.size();
				}
					
			};
			this.jdbcTemplate.batchUpdate(sql, bts);
		     ((ConfigurableApplicationContext)context).close();

	}
}