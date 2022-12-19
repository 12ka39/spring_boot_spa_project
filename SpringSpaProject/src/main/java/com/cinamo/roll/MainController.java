package com.cinamo.roll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index"); //WEB-INF/view/index.jsp
		
			/* ~ ModelAndView ~
			 ModelAndView는 컴포넌트 즉 객체 방식으로 작성되고 돌려준다.
			그래서 인자가 없으며 돌려주는 데이터형도 ModelAndView이다.
			
			또한 ModelAndView 객체를 생성한다.
			예) ModelAndView mv = new ModelAndView();
			
			데이터 추가는 addObject(key, value)로 추가하며,
			페이지 이동값은 setViewName로 페이지를 세팅한다.
			
			return 값은 ModelAndViewe(위에는 mv)로 돌려준다.
			 
			추가적으로 Model과 ModelAndView의 구체적인 차이를 설명하자면
			Model -> model.addAttribute를 사용하여 데이터만 저장
			ModelAndView -> 데이터와 이동하고자 하는 View Page를 같이 저장
			 */
		return mv;
	}
}
