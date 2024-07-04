package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyZasekiController {

	//	int[] array = new int[10];
	//	String[] arr = new String[10];

	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.GET)
	public String get(HttpSession session) {

		return "myzaseki";
	}

	//	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.POST)
	//	public String post(HttpSession session, String yoyaku, String system1, String system2,
	//			String system3,
	//			String system4, String month, String month2) {
	//
	//		
	//
	//		List<Integer> list = new ArrayList<Integer>();
	//
	//		ArrayList<String> li;
	//		if(session.getAttribute("num") == null) {
	//			li = new ArrayList<String>();
	//		} else {
	//			li = (ArrayList<String>)session.getAttribute("num");
	//		}
	//		
	//		
	//		int index = 0;
	//
	//		if (yoyaku == null && system1 == null && system2 == null && system3 == null && system4 == null &&
	//				month == null && month2 == null) {
	//			return "myzaseki";
	//		} else if (yoyaku == null && system1 != null && month != null && month2 == null && system4 == null) {
	//			int sys = Integer.parseInt(system1);
	//			int mt = Integer.parseInt(month);
	//			if (sys >= 1 && sys < 11 && mt >= 1 && mt < 13) {
	//				list.add(sys);
	//				li.add(system1 + "番" + "　" + month + "月");
	//				session.setAttribute("num", li);
	//				index = list.indexOf(sys);
	//				array[index] = mt;
	//				arr[index] = "番" + "　" + month + "月";
	//			} else {
	//				return "myzaseki";
	//			}
	//		} else if (yoyaku == null && system2 != null && system3 != null && month2 != null && month == null
	//				&& system4 == null) {
	//			int sys2 = Integer.parseInt(system2);
	//			int sys3 = Integer.parseInt(system3);
	//			int mt2 = Integer.parseInt(month2);
	//			if (sys2 >= 1 && sys2 < 11 && sys3 >= 1 && sys3 < 11 && mt2 >= 1 && mt2 < 13) {
	//				list.add(sys2);
	//				list.add(sys3);
	//				li.add(system2 + "番" + "　" + month2 + "月");
	//				li.add(system3 + "番" + "　" + month2 + "月");
	//				session.setAttribute("num", li);
	//				index = list.indexOf(sys2);
	//				array[index] = mt2;
	//				array[index + 1] = mt2;
	//				arr[index] = "番" + "　" + month2 + "月";
	//				arr[index + 1] = "番" + "　" + month2 + "月";
	//			} else {
	//				return "myzaseki";
	//			}
	//		} else if (yoyaku == null && system4 != null && system2 == null && system3 == null && month == null
	//				&& month2 == null) {
	//			int sys4 = Integer.parseInt(system4);
	//			System.out.println(sys4 + "sys4の値");
	//			index = list.indexOf(sys4);
	//			System.out.println(index + "indexの値");
	//			int listSize = list.size();
	//			System.out.println(listSize + "リストサイズ");
	//			if (index >= 1) {
	//				arr[index - 1] = system4 + arr[index];
	//				array[index - 1] = sys4;
	//				list.remove(listSize - sys4);
	//				li.remove(system4 + arr[index]);
	//				session.setAttribute("num", li);
	//				arr[index] = "";
	//				array[index] = 0;
	//			} else {
	////				list.remove(sys4);
	//				li.remove(system4 + arr[index]);
	//				session.setAttribute("num", li);
	//			}
	//
	//		}
	//		session.setAttribute("yoyaku", yoyaku);
	//		return "myzaseki";
	//
	//	}

	//ここから下がPOSTメソッド内の全処理

	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.POST)
	public String post(Model model, HttpSession session, String yoyaku, String system1, String system2,
			String system3,
			String system4, String system5, String month, String month2) {

		//追加仕様の重複チェックまで終わっています。1~5まで一気に予約する処理は出来ていません。
		//追加で全取消の処理を作りました。

		//liはString型のリスト
		//listはInteger型のリスト

		ArrayList<String> li;
		ArrayList<Integer> list;
		String chohuku;

		int count = 0;
		if (session.getAttribute("num") == null && session.getAttribute("num2") == null
				&& model.getAttribute("chohuku") == null) {
			li = new ArrayList<String>();
			list = new ArrayList<Integer>();
			chohuku = "";

			//リストに予約番号と月を追加する。

			//STEP1.リストをセッションに格納する。
		} else {
			li = (ArrayList<String>) session.getAttribute("num");
			list = (ArrayList<Integer>) session.getAttribute("num2");
			chohuku = (String) model.getAttribute("chohuku");
			chohuku = "";
			//リストに予約番号と月を追加する。
			if (yoyaku == null && system1 == null && system2 == null && system3 == null && system4 == null
					&& month == null &&
					month2 == null && system5 == null) {
				return "myzaseki";
				//予約の処理
			} else if (yoyaku == null && system1 != null && system2 == null && system3 == null &&
					system4 == null && month != null && month2 == null && system5 == null) {
				int sys = Integer.parseInt(system1);
				int mt = Integer.parseInt(month);
				//重複チェック↓
				//予約番号が1~10の時、月が1~12の時に通るようにする処理↓
				if (sys >= 1 && sys < 11 && mt >= 1 && mt < 13) {
					//リストの中身が空っぽの時
					if (list.size() == 0) {
						li.add(system1 + "番" + "　" + month + "月");
						list.add(sys);
						session.setAttribute("num", li);
						session.setAttribute("num2", list);
					} else if (list.size() == 1) {
						//リストの中身が1個の時
						for (int i = 0; i < list.size(); i++) {
							count++;
							//重複した時
							if (list.get(i) == sys) {
								chohuku = "重複";
								session.setAttribute("num", li);
								session.setAttribute("num2", list);
								session.setAttribute("chohuku", chohuku);
								session.setAttribute("yoyaku2", "予約");
								//重複していない時
							} else if (list.get(i) != sys && count == list.size()) {
								chohuku = "";
								li.add(system1 + "番" + "　" + month + "月");
								list.add(sys);
								session.setAttribute("num", li);
								session.setAttribute("num2", list);
								model.addAttribute("chohuku", chohuku);
								break;
							}
						}
						//リストの中身が2個以上ある時
					} else if (list.size() >= 2) {
						for (int i = 0; i < list.size(); i++) {
							count++;
							//重複した時
							if (list.get(i) == sys) {
								chohuku = "重複";
								session.setAttribute("num", li);
								session.setAttribute("num2", list);
								model.addAttribute("chohuku", chohuku);
								session.setAttribute("yoyaku2", "予約");
								break;
								//重複していない時
							} else if (list.get(i) != sys && count == list.size()) {
								if (count >= 2) {
									chohuku = "";
									li.add(system1 + "番" + "　" + month + "月");
									list.add(sys);
									session.setAttribute("num", li);
									session.setAttribute("num2", list);
									model.addAttribute("chohuku", chohuku);
									break;
								}
							}
						}
					}
					//ここまで重複チェック↑

				} else {
					return "myzaseki";
				}
				//まとめて予約の処理
			} else if (yoyaku == null && system2 != null && system3 != null && month2 != null && system1 == null &&
					system4 == null && month == null && system5 == null) {
				int sys2 = Integer.parseInt(system2);
				int sys3 = Integer.parseInt(system3);
				int mt2 = Integer.parseInt(month2);
				int mt3 = Integer.parseInt(month2);
				//予約番号が1~10の時、月が1~12の時に通るようにする
				if (sys2 != sys3) {
					if (sys2 >= 1 && sys2 < 11 && sys3 >= 1 && sys3 < 13 && mt2 >= 1 && mt2 < 13 && mt3 >= 1
							&& mt3 < 13) {
						//重複チェック↓
						//リストの中身が空っぽの時
						if (list.size() == 0) {
							li.add(system2 + "番" + "　" + month2 + "月");
							li.add(system3 + "番" + "　" + month2 + "月");
							list.add(sys2);
							list.add(sys3);
							session.setAttribute("num", li);
							session.setAttribute("num2", list);
							//リストの中身が1個の時
						} else if (list.size() == 1) {
							for (int i = 0; i < list.size(); i++) {
								count++;
								//重複した時
								if (list.get(i) == sys2 || list.get(i) == sys3) {
									chohuku = "重複";
									session.setAttribute("num", li);
									session.setAttribute("num2", list);
									model.addAttribute("chohuku", chohuku);
									session.setAttribute("yoyaku2", "まとめて予約");
									break;
									//重複していない時
								} else if ((list.get(i) != sys2 || list.get(i) != sys3) && count == list.size()) {
									chohuku = "";
									li.add(system2 + "番" + "　" + month2 + "月");
									li.add(system3 + "番" + "　" + month2 + "月");
									list.add(sys2);
									list.add(sys3);
									session.setAttribute("num", li);
									session.setAttribute("num2", list);
									model.addAttribute("chohuku", chohuku);
									break;
								}
							}
							//リストが2個以上ある時
						} else if (list.size() >= 2) {
							for (int i = 0; i < list.size(); i++) {
								count++;
								//重複した時
								if (list.get(i) == sys2 || list.get(i) == sys3) {
									chohuku = "重複";
									session.setAttribute("num", li);
									session.setAttribute("num2", list);
									model.addAttribute("chohuku", chohuku);
									session.setAttribute("yoyaku2", "まとめて予約");
									break;
									//重複していない時
								} else if ((list.get(i) != sys2 || list.get(i) != sys3) && count == list.size()) {
									if (count >= 2) {
										chohuku = "";
										li.add(system2 + "番" + "　" + month2 + "月");
										li.add(system3 + "番" + "　" + month2 + "月");
										list.add(sys2);
										list.add(sys3);
										session.setAttribute("num", li);
										session.setAttribute("num2", list);
										model.addAttribute("chohuku", chohuku);
										break;
									}
								}
							}
						}
						//ここまで重複チェック↑
					} else {
						return "myzaseki";
					}

				} else {
					return "myzaseki";
				}
				//取消の処理
			} else if (yoyaku == null && system4 != null && system1 == null && system2 == null
					&& system3 == null
					&& month == null && month2 == null && system5 == null) {
				int sys4 = Integer.parseInt(system4);
				int index = list.indexOf(sys4);
				//リストの中身が空っぽまたは存在しない値を消そうとした時の処理
				if (list.size() != 0 && index != -1) {
					chohuku = "";
					list.remove(index);
					li.remove(index);
					session.setAttribute("num", li);
					session.setAttribute("num2", list);
					model.addAttribute("chohuku", chohuku);
				} else if (list.size() == 0 || index == -1) {
					return "myzaseki";
				}
				//全取消の処理
			} else if (yoyaku == null && "DELETE".equals(system5) && system4 == null && system1 == null
					&& system2 == null
					&& system3 == null
					&& month == null && month2 == null) {
				chohuku = "";
				list.clear();
				li.clear();
				session.setAttribute("num", li);
				session.setAttribute("num2", list);
				model.addAttribute("chohuku", chohuku);

			}

			//STEP1.リストをセッションに格納する。
		}
		model.addAttribute("chohuku", chohuku);
		session.setAttribute("num", li);
		session.setAttribute("num2", list);
		session.setAttribute("yoyaku", yoyaku);

		return "myzaseki";

	}

}