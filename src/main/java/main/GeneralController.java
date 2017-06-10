package main; 


import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.stereotype.Controller;    
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bean.Cinema;
import bean.Comment;
import bean.Movie;
import bean.Order;
import bean.Schedule;
import bean.Seat;
import bean.User;
import dao.CinemaDao;
import dao.CommentDao;
import dao.MovieDao;
import dao.OrderDao;
import dao.ScheduleDao;
import dao.SeatDao;
import dao.UserDao;
import net.sf.json.JSONObject;    
    
@Controller
public class GeneralController {

	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    //return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	    return "202.96.128.86";//����
	}
	
    /** 
     * unicode ת���� ���� 
     *  
     * @author fanhui 2007-3-15 
     * @param theString 
     * @return 
     */  
    public static String decodeUnicode(String theString) {  
        char aChar;  
        int len = theString.length();  
        StringBuffer outBuffer = new StringBuffer(len);  
        for (int x = 0; x < len;) {  
            aChar = theString.charAt(x++);  
            if (aChar == '\\') {  
                aChar = theString.charAt(x++);  
                if (aChar == 'u') {  
                    int value = 0;  
                    for (int i = 0; i < 4; i++) {  
                        aChar = theString.charAt(x++);  
                        switch (aChar) {  
                        case '0':  
                        case '1':  
                        case '2':  
                        case '3':  
                        case '4':  
                        case '5':  
                        case '6':  
                        case '7':  
                        case '8':  
                        case '9':  
                            value = (value << 4) + aChar - '0';  
                            break;  
                        case 'a':  
                        case 'b':  
                        case 'c':  
                        case 'd':  
                        case 'e':  
                        case 'f':  
                            value = (value << 4) + 10 + aChar - 'a';  
                            break;  
                        case 'A':  
                        case 'B':  
                        case 'C':  
                        case 'D':  
                        case 'E':  
                        case 'F':  
                            value = (value << 4) + 10 + aChar - 'A';  
                            break;  
                        default:  
                            throw new IllegalArgumentException(  
                                    "Malformed      encoding.");  
                        }  
                    }  
                    outBuffer.append((char) value);  
                } else {  
                    if (aChar == 't') {  
                        aChar = '\t';  
                    } else if (aChar == 'r') {  
                        aChar = '\r';  
                    } else if (aChar == 'n') {  
                        aChar = '\n';  
                    } else if (aChar == 'f') {  
                        aChar = '\f';  
                    }  
                    outBuffer.append(aChar);  
                }  
            } else {  
                outBuffer.append(aChar);  
            }  
        }  
        return outBuffer.toString();  
    } 
	
    
    public List<Movie> index_movie() {
    	MovieDao movieDao = new MovieDao();
    	List<Movie> movies = movieDao.GetMovies();//��ʱ�趨Ϊ��ȡȫ��ӰƬ
    	Compare_money compare_money  = new Compare_money(); 
    	Collections.sort(movies, compare_money);
    	
    	if (movies.size() < 5) return movies;
    	else return movies.subList(0, 5);
    }
    public class Compare_money implements Comparator {
    	public int compare(Object obj0, Object obj1) {
        	Movie movie1 = (Movie)obj0;
        	Movie movie2 = (Movie)obj1;
        	int flag = movie2.getNow_ReceivedMoney() > movie1.getNow_ReceivedMoney() ? 1 : -1;
        	return flag;
        }
    }
    
    public List<Cinema> index_cinema() {
    	CinemaDao cinemaDao = new CinemaDao();
    	List<Cinema> cinemas = cinemaDao.Getcinemas();//��ʱ�趨Ϊ��ȡȫ��ӰԺ
    	return cinemas;
    }
    
    public class Compare_time implements Comparator {
    	public int compare(Object obj0, Object obj1) {
        	Movie movie1 = (Movie)obj0;
        	Movie movie2 = (Movie)obj1;
        	int flag = movie2.getOn_time().before(movie1.getOn_time()) ? 1 : -1;
        	return flag;
        }
    }
    public List<Movie> lastest_movie() {
    	MovieDao movieDao = new MovieDao();
    	List<Movie> movies = movieDao.GetMovies();//��ʱ�趨Ϊ��ȡȫ��ӰƬ
    	Compare_time compare_time = new Compare_time();
    	Collections.sort(movies, compare_time);
    	if (movies.size() < 5) return movies;
    	else return movies.subList(0, 5);
    }
    @RequestMapping(value={"/index", "/"})
    public String index_jsp(Model model,  HttpSession session,  HttpServletRequest request){
    	//��ȡ����λ��
    	String ip = getRemoteHost(request);
    	try {
    		URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
    		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
    		InputStream res = urlConn.getInputStream();
    	    Scanner scanner = new Scanner(res);
    	    String urlContent = "";
    	    while (scanner.hasNextLine()) {
    	        urlContent += (String)scanner.nextLine();
    	    }
    	    System.out.println(urlContent);
    	    String[] temp = urlContent.split(",");
    	    String region = (temp[7].split(":"))[1].replaceAll("\"", "");  
            region = decodeUnicode(region);// ʡ��
            System.out.println(region);
            model.addAttribute("region", region);
            session.setAttribute("region", region);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	List<Movie> movies_to_show = index_movie();
    	model.addAttribute("movies", movies_to_show);
    	
    	List<Cinema> cinemas_to_show = index_cinema();
    	model.addAttribute("cinemas", cinemas_to_show);
    	
    	List<Movie> movies_lates = lastest_movie();
    	model.addAttribute("movies_late", movies_lates);
        model.addAttribute("name", session.getAttribute("username"));
        return "index";
    }
    //��¼
    @RequestMapping(value = "/login", method = RequestMethod.GET)    
    public String login_jsp(Model model){
    	return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user,  HttpSession session, Model model) {
    	UserDao userDao = new UserDao();
    	if (user.getUsername().equals("eggplant")) {
    		User admin_user = userDao.GetUserbyName("eggplant");
    		if (admin_user.getPassword().equals(user.getPassword())) {
    			session.setAttribute("username", "eggplant");
    			return "redirect:admin";
    		}
    	}
		User real_user = userDao.GetUserbyName(user.getUsername());
    	if (real_user != null && user.getPassword().equals(real_user.getPassword())) {
    		session.setAttribute("username", real_user.getUsername());
        	session.setAttribute("userId", real_user.getUserId());
        	model.addAttribute("name", session.getAttribute("username"));
    	}
    	return "redirect:index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ResponseBody
    public String login_validation(@RequestBody Map<Object, Object> user,  HttpSession session) {
    	UserDao userDao = new UserDao();
    	User real_user = userDao.GetUserbyName(user.get("username").toString());
    	String result = "Yes";
    	if (real_user.getUsername() == null) {
    		result = "username";
    	} else if (!real_user.getPassword().equals(user.get("password").toString())) {
    		result = "password";
    	}
		System.out.println(real_user.getPassword() + " " + user.get("password").toString());
    	return result;
    }
    
    //ע��
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String regist_jsp(Model model){
    	return "regist";
    }
    @RequestMapping(value="/regist", method = RequestMethod.POST)
    public String regist(User user, Model model, HttpSession session) {
    	System.out.println(user.getUsername() + "-" + user.getPassword() + "-" + user.getPhoneNumber());
    	UserDao userDao = new UserDao();
        int uId = userDao.addUser(user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userId", uId);
    	return "redirect:index";
    }
    @RequestMapping(value="/regist", method = RequestMethod.PUT)
    @ResponseBody
    public String regist_username(@RequestBody Map<Object, Object> username, Model model, HttpSession session) {
    	UserDao userDao = new UserDao();
    	boolean has = userDao.queryUserbyName(username.get("username").toString());
    	if (has) {
    		return "YES";
    	} else {
    		return "NO";
    	}
    	
    }
    
    //�û�����
    @RequestMapping(value="/info")
    public String info_jsp(Model model,  HttpSession session) {
        model.addAttribute("name", session.getAttribute("username"));
        return "info";
    }
    @RequestMapping(value="/info", method = RequestMethod.POST)
    public String info_(HttpServletRequest request,  HttpSession session, Model model) {
    	System.out.println(request.getParameter("name"));
    	UserDao userDao = new UserDao();
        userDao.UpdateUserName(request.getParameter("name"), (String)session.getAttribute("username"));
        session.setAttribute("username", request.getParameter("name"));
        
        model.addAttribute("name", session.getAttribute("username"));
        return "redirect:info";
    }

    //��Ӱ����
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String movie_jsp(@PathVariable int id, Model model, HttpSession session) {
    	MovieDao movieDao = new MovieDao();
    	Movie movie = movieDao.GetMovieFromID(id);
    	//������ʾ
    	CommentDao commentDao = new CommentDao();
    	List<Comment> comments =  commentDao.getAllComments(id);
    	model.addAttribute("comments", comments);
    	model.addAttribute("movie", movie);
    	model.addAttribute("name", session.getAttribute("username"));
    	return "movie";
    }
    //��Ӱ����ҳ����������
    @ResponseBody
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.POST)    
    public Object movie_comment(@PathVariable int id, Model model, @RequestParam("comment") String comment, @RequestParam("deleteId") String commentIdToDelete, HttpSession session) {
    	CommentDao commentDao = new CommentDao();
    	//����
			System.out.println(comment);
			System.out.println(commentIdToDelete);
		//Ҫɾ��ʱ
		JSONObject jsonObject = new JSONObject();
    	if (commentIdToDelete != "") {
    		commentDao.DeleteComment(Integer.parseInt(commentIdToDelete));
    		jsonObject.put("isDelete", "YES");
    		jsonObject.put("commentId", commentIdToDelete);
    		jsonObject.put("username", "");
    		jsonObject.put("mid", "");
    		jsonObject.put("comment", comment);
    		return jsonObject;
    	} else if (comment != "") {//Ҫ���ʱ
    		jsonObject.put("isDelete", "NO");
    		
    		Comment _comment = new Comment();
        	if (session.getAttribute("username") == null) {
        		_comment.setUserName("·��");
        		jsonObject.put("username", "·��");
        	} else {
        		_comment.setUserName((String)session.getAttribute("username"));
        		jsonObject.put("username", (String)session.getAttribute("username"));
        	}
        	_comment.setmId(id);
        	jsonObject.put("mid", id);
        	_comment.setCommentText(comment);
        	jsonObject.put("comment", comment);
        	int cId = commentDao.AddComment(_comment);
        	jsonObject.put("commentId", cId);
    		return jsonObject;
    	} else {
    		return null;
    	}
    }

    //���е�Ӱ
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String movies_jsp(Model model, HttpSession session) {
    	MovieDao movieDao = new MovieDao();
    	List<Movie> movies = movieDao.GetMovies();
    	model.addAttribute("movies", movies);
    	model.addAttribute("name", session.getAttribute("username"));
    	return "movies";
    }
    
    //ӰԺ��Ϣ
    @RequestMapping(value="/cinema/{id}", method = RequestMethod.GET)
    public String cinema_jsp(@PathVariable int id, Model model, HttpSession session) {
    	ScheduleDao scheduleDao = new ScheduleDao();
    	List<Schedule> schedules = scheduleDao.GetschedulesByCid(id);
    	CinemaDao cinemaDao = new CinemaDao();
    	Cinema cinema = cinemaDao.GetCinema(id);
    	model.addAttribute("cinema", cinema);
    	model.addAttribute("schedules", schedules);
    	model.addAttribute("name", session.getAttribute("username"));
    	return "cinema";
    }
    
    //����ӰԺ
    @RequestMapping(value="/cinemas", method = RequestMethod.GET)
    public String cinemas_jsp(Model model, HttpSession session) {
    	CinemaDao cinemaDao = new CinemaDao();
    	List<Cinema> cinemas = cinemaDao.Getcinemas();
    	model.addAttribute("cinemas", cinemas);
    	model.addAttribute("name", session.getAttribute("username"));
    	return "cinemas";
    }
    //��Ʊ
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String book_jsp(@PathVariable int id, Model model, HttpSession session) {
    	ScheduleDao scheduleDao = new ScheduleDao();
    	List<Schedule> schedules = scheduleDao.GetschedulesByMid(id);
    	model.addAttribute("schedules", schedules);
    	model.addAttribute("id", id);
    	model.addAttribute("name", session.getAttribute("username"));
    	session.setAttribute("movieId", id);
    	return "book";
    }
    
    //ѡ��
    @RequestMapping(value = "/choose_seat/{sId}", method = RequestMethod.GET)
    public String choose_seat_jsp(@PathVariable int sId, Model model, HttpSession session) {
    	SeatDao seatDao = new SeatDao();
    	List<Seat> seats = seatDao.GetUsedSeatsBySid(sId);
    	model.addAttribute("seats", seats);
    	model.addAttribute("sId", sId);
    	model.addAttribute("movieId", session.getAttribute("movieId"));
    	model.addAttribute("name", session.getAttribute("username"));
    	return "choose_seat";
    }
    
    //ѡ��
    @RequestMapping(value = "/choose_seat/{sId}", method = RequestMethod.POST)
    @ResponseBody
    public String choose_seat(@PathVariable int sId, Model model, HttpSession session,  @RequestParam("seatRow") int seatRow, @RequestParam("seatcolumn") int seatColumn) {
    	SeatDao seatDao = new SeatDao();
    	Seat seat = new Seat();
    	seat.setsId(sId);
    	seat.setSeatRow(seatRow);
    	seat.setSeatColumn(seatColumn);
    	int seatId = seatDao.addUsedSeat(seat);//�����ݿ����һ����λ��ѡ�˵���Ϣ
    	session.setAttribute("seatId", seatId);
    	session.setAttribute("sId", sId);
    	return "../order/";
    }
    
    //��һ�ζ���
    @RequestMapping(value = "/order/", method = RequestMethod.GET)
    public String first_order(Model model, HttpSession session) {
    	//���һ�����������ݿ�
    	OrderDao orderDao = new OrderDao();
    	Order order = new Order();
    	order.setSeatId(Integer.parseInt(session.getAttribute("seatId").toString()));
    	order.setsId(Integer.parseInt(session.getAttribute("sId").toString()));
    	order.setuId(Integer.parseInt(session.getAttribute("userId").toString()));
    	ScheduleDao scheduleDao = new ScheduleDao();
    	Schedule schedule = scheduleDao.GetSchedule(Integer.parseInt(session.getAttribute("sId").toString()));
    	order.setOrderPrice(schedule.getPrice());
    	order.setBuyDate(null);
    	order.setStatus("δ֧��");
    	int oId = orderDao.addOrder(order);
    	order.setoId(oId);
    	model.addAttribute("order", order);
    	model.addAttribute("movieId", session.getAttribute("movieId"));
    	model.addAttribute("sId", Integer.parseInt(session.getAttribute("sId").toString()));
    	model.addAttribute("name", session.getAttribute("username"));
    	return "order";
    }
    
    //����
    @RequestMapping(value = "/order/{oId}", method = RequestMethod.GET)
    public String order(@PathVariable int oId, Model model, HttpSession session) {
    	//���һ�����������ݿ�
    	OrderDao orderDao = new OrderDao();
    	Order order = orderDao.GetOrderbyOid(oId);
     	model.addAttribute("order", order);
     	ScheduleDao scheduleDao = new ScheduleDao();
     	Schedule schedule = scheduleDao.GetSchedule(order.getsId());
    	model.addAttribute("movieId", schedule.getmId());
    	model.addAttribute("sId", order.getsId());
    	model.addAttribute("name", session.getAttribute("username"));
    	session.setAttribute("seatId", order.getSeatId());
    	return "order";
    }

    //����Ա
    //��
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin_jsp(Model model, HttpSession session) {
    	MovieDao movieDao = new MovieDao();
    	List<Movie> movies = movieDao.GetMovies();
    	CinemaDao cinemaDao = new CinemaDao();
    	List<Cinema> cinemas = cinemaDao.Getcinemas();
    	ScheduleDao scheduleDao = new ScheduleDao();
    	List<Schedule> schedules = scheduleDao.Getschedules();
    	model.addAttribute("schedules", schedules);
    	model.addAttribute("cinemas", cinemas);
    	model.addAttribute("movies", movies);
    	return "admin";
    }
    //��Ӱ
    @RequestMapping(value = "/admin_movie", method = RequestMethod.GET)
    public String admin_movie_jsp(Model model, HttpSession session) {
    	return "Admin_Movie";
    }
    	//��
    @RequestMapping(value = "/admin_movie", method = RequestMethod.POST)
    public String admin_movie(@RequestParam("Post_big") MultipartFile big_post, 
    		@RequestParam("Post") MultipartFile post, @RequestParam("Trailer") MultipartFile trail, 
    		@RequestParam("movie_name") String movie_name, @RequestParam("main_actors") String main_actors, 
    		@RequestParam("movie_description") String movie_description, @RequestParam("movie_intro") String movie_intro, 
    		@RequestParam("movie_style") String movie_style, @RequestParam("movie_director") String movie_director,
    		@RequestParam("movie_span") String movie_span, @RequestParam("On_time") Date On_time,
    		@RequestParam("End_time") Date End_time,
    		Model model, HttpSession session, HttpServletRequest request) {
    	System.out.println("hah1");
    	MovieDao movieDao = new MovieDao();
    	Movie movie = new Movie();
    	movie.setEnd_time(End_time);
    	movie.setMain_actors(main_actors);
    	movie.setMovie_description(movie_description);
    	movie.setMovie_director(movie_director);
    	movie.setMovie_intro(movie_intro);
    	movie.setMovie_name(movie_name);
    	movie.setMovie_span(movie_span);
    	movie.setMovie_style(movie_style);
    	movie.setNow_ReceivedMoney(0);
    	movie.setOn_time(On_time);
    	int mId = movieDao.AddMovie(movie);
    	String post_path = request.getSession().getServletContext().getRealPath("/") + "images/movie/";
    	String video_path =request.getSession().getServletContext().getRealPath("/") + "video/";
    	try {
    		big_post.transferTo(new File(post_path + mId + "_big.jpg"));
    		post.transferTo(new File(post_path + mId + ".jpg"));
    		trail.transferTo(new File(video_path + mId + ".mp4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "redirect:admin";
    }
    	//ɾ
    @RequestMapping(value = "/admin/movie_delete/{mId}", method = RequestMethod.DELETE)
    public String admin_movie_delete(@PathVariable int mId, Model model, HttpSession session, HttpServletRequest request) {
    	MovieDao movieDao = new MovieDao();
    	movieDao.DeleteMovie(mId);
    	String post_path = request.getSession().getServletContext().getRealPath("/") + "images/movie/";
    	String video_path =request.getSession().getServletContext().getRealPath("/") + "video/";
    	File file = new File(post_path + mId + "_big.jpg");
    	file.delete();
    	file = new File(post_path + mId + ".jpg");
    	file.delete();
    	file = new File(video_path + mId + ".mp4");
    	file.delete();
    	return "redirect:../../admin";
    }
		//��
    @RequestMapping(value = "/admin_movie/{mId}", method = RequestMethod.GET)
    public String admin_movie_update_jsp(@PathVariable int mId, Model model, HttpSession session) {
    	MovieDao movieDao = new MovieDao();
    	Movie movie = movieDao.GetMovieFromID(mId);
    	model.addAttribute("movie", movie);
    	return "Admin_Movie";
    }
    @RequestMapping(value = "/admin_movie/{mId}", method = RequestMethod.POST)
    public String admin_movie_update(@PathVariable int mId,  
    		@RequestParam("movie_name") String movie_name, @RequestParam("main_actors") String main_actors, 
    		@RequestParam("movie_description") String movie_description, @RequestParam("movie_intro") String movie_intro, 
    		@RequestParam("movie_style") String movie_style, @RequestParam("movie_director") String movie_director,
    		@RequestParam("movie_span") String movie_span, @RequestParam("On_time") Date On_time,
    		@RequestParam("End_time") Date End_time, Model model, HttpSession session) {
    	MovieDao movieDao = new MovieDao();
    	Movie movie = new Movie();
    	movie.setEnd_time(End_time);
    	movie.setMain_actors(main_actors);
    	movie.setMovie_description(movie_description);
    	movie.setMovie_director(movie_director);
    	movie.setMovie_intro(movie_intro);
    	movie.setMovie_name(movie_name);
    	movie.setMovie_span(movie_span);
    	movie.setMovie_style(movie_style);
    	movie.setNow_ReceivedMoney(0);
    	movie.setOn_time(On_time);
    	movieDao.UpdateMovie(movie, mId);
    	return "redirect:../admin";
    }
    //ӰԺ
    @RequestMapping(value = "/admin_cinema", method = RequestMethod.GET)
    public String admin_cinema_jsp(Model model, HttpSession session) {
    	return "Admin_Cinema";
    }
		//��
    @RequestMapping(value = "/admin_cinema", method = RequestMethod.POST)
    public String admin_cinema(Cinema cinema, Model model, HttpSession session) {
    	CinemaDao cinemaDao = new CinemaDao();
    	cinemaDao.addCinema(cinema);
    	return "redirect:admin";
    }
    	//��
    @RequestMapping(value = "/admin_cinema/{cId}", method = RequestMethod.GET)
    public String admin_cinema_update_jsp(@PathVariable int cId, Model model, HttpSession session) {
    	CinemaDao cinemaDao = new CinemaDao();
    	Cinema cinema = cinemaDao.GetCinema(cId);
    	model.addAttribute("cinema", cinema);
    	return "Admin_Cinema";
    }
    @RequestMapping(value = "/admin_cinema/{cId}", method = RequestMethod.POST)
    public String admin_cinema_update(@PathVariable int cId, Cinema cinema, Model model, HttpSession session) {
    	CinemaDao cinemaDao = new CinemaDao();
    	cinemaDao.UpdateCinema(cinema, cId);
    	model.addAttribute("cinema", cinema);
    	return "redirect:../admin";
    }
    	//ɾ
    @RequestMapping(value = "/admin/cinema_delete/{cId}", method = RequestMethod.DELETE)
    public String admin_cinema_delete(@PathVariable int cId, Model model, HttpSession session) {
    	CinemaDao cinemaDao = new CinemaDao();
    	cinemaDao.DeleteCinema(cId);
    	return "redirect:../../admin";
    }
    
    //Schedule
    @RequestMapping(value = "/admin_schedule", method = RequestMethod.GET)
    public String admin_schedule_jsp(Model model, HttpSession session) {
    	return "Admin_Schedule";
    }
    	//add
    @RequestMapping(value = "/admin_schedule", method = RequestMethod.POST)
    public String admin_schedule(@RequestParam("mId") String mId, @RequestParam("cId") String cId, 
    		@RequestParam("price") String price, @RequestParam("hallName") String hallName,
    		@RequestParam("startDate") Date startDate, @RequestParam("startTime") String startTime, 
    		Model model, HttpSession session) {
    	ScheduleDao scheduleDao = new ScheduleDao();
    	Schedule schedule = new Schedule();
    	Time _startTime = null;
    	try {
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        	_startTime = new Time(simpleDateFormat.parse(startTime).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	schedule.setmId(Integer.parseInt(mId));
    	schedule.setHallName(hallName);
    	schedule.setPrice(price);
    	schedule.setcId(Integer.parseInt(cId));
    	schedule.setStartDate(startDate);
    	schedule.setStartTime(_startTime);
    	scheduleDao.addSchedule(schedule);
    	return "redirect:admin";
    }
    	//ɾ
    @RequestMapping(value = "/admin/schedule_delete/{sId}", method = RequestMethod.DELETE)
    public String admin_schedule_delete(@PathVariable int sId, Model model, HttpSession session) {
    	ScheduleDao scheduleDao = new ScheduleDao();
    	scheduleDao.DeleteSchedule(sId);
    	return "redirect:../../admin";
    }
    	//��
    @RequestMapping(value = "/admin_schedule/{sId}", method = RequestMethod.GET)
    public String admin_schedule_update_jsp(@PathVariable int sId, Model model, HttpSession session) {
    	ScheduleDao scheduleDao = new ScheduleDao();
    	Schedule schedule = scheduleDao.GetSchedule(sId);
    	model.addAttribute("schedule", schedule);
    	return "Admin_Schedule";
    }
    @RequestMapping(value = "/admin_schedule/{sId}", method = RequestMethod.POST)
    public String admin_schedule_update(@PathVariable int sId, @RequestParam("mId") String mId, @RequestParam("cId") String cId, 
    		@RequestParam("price") String price, @RequestParam("hallName") String hallName,
    		@RequestParam("startDate") Date startDate, @RequestParam("startTime") String startTime,  Model model, HttpSession session) {
    	ScheduleDao scheduleDao = new ScheduleDao();
    	Schedule schedule = new Schedule();
    	Time _startTime = null;
    	try {
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        	_startTime = new Time(simpleDateFormat.parse(startTime).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	schedule.setmId(Integer.parseInt(mId));
    	schedule.setHallName(hallName);
    	schedule.setPrice(price);
    	schedule.setcId(Integer.parseInt(cId));
    	schedule.setStartDate(startDate);
    	schedule.setStartTime(_startTime);
    	scheduleDao.UpdateSchedule(schedule, sId);
    	return "redirect:../admin";
    }
    //help�ĵ�
    @RequestMapping(value="/help", method = RequestMethod.GET)
    public String help(Model model, HttpSession session){
    	model.addAttribute("name", session.getAttribute("username"));
    	return "help";
    }
    //֧��
    @RequestMapping(value="/pay")
    public String pay_jsp(Model model,  HttpSession session) {
        model.addAttribute("name", session.getAttribute("username"));
        return "pay";
    }
}