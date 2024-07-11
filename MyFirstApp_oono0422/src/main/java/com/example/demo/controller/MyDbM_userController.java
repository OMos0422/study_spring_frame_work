package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyDbM_userController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/classupd", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user");
		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);
		model.addAttribute("record", kensakukekka.size());

		return "mydbm_user";
	}

	@RequestMapping(path = "/classSer", method = RequestMethod.POST)
	public String dbfg2(Model model, String cls) {

		//検索処理
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user WHERE class = ?", cls);
		if (kensakukekka.size() != 0) {
			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
		} else {
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user");
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
		}

		return "mydbm_user";
	}

	@RequestMapping(path = "/nameSer", method = RequestMethod.POST)
	public String dbfg4(Model model, String name) {
		name = "%" + name + "%";
		//検索処理
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user WHERE user_name LIKE ?", name);
		if (kensakukekka.size() != 0) {
			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
		} else {
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user");
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
		}

		return "mydbm_user";
	}

	@RequestMapping(path = "/classupd2", method = RequestMethod.POST)
	public String dbfg6(Model model, String name, String id1, String id2, String upd, String upd2) {
		//検索処理
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user WHERE user_name LIKE ?", name);
		if (kensakukekka.size() != 0) {
			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
		} else {
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user");
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
		}

		return "mydbm_user";
	}

	//練習問題③用
	@RequestMapping(path = "/classupd", method = RequestMethod.POST)
	public String dbfg3(HttpSession session, Model model, String upd, String id1, String id2) {

		List<Map<String, Object>> kensakukekka;
		Map<String, String> begin;
		Map<String, String> last;
		//Mapを作成↓
		if (session.getAttribute("map1") == null && session.getAttribute("map2") == null) {

			begin = new HashMap<>();
			last = new HashMap<>();

		} else {

			begin = (Map<String, String>) session.getAttribute("map1");
			last = (Map<String, String>) session.getAttribute("map2");
		}

		kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ?;", upd, id1,
						id2);

		//存在するまたは何年制か知っているクラスの更新処理↓
		if (kensakukekka.size() != 0 && upd != null && !("卒業生".equals(upd)) && id1 != null && id2 != null) {
			//例)３Aの３を抽出し、int型にする処理 upd2↓

			int upd2 = Integer.parseInt(upd.substring(0, 1));
			//例)３AのAを抽出する処理 upd3↓
			String upd3 = upd.substring(1, 2);
			//最終的に上記２つを結合する変数 upd4↓
			String upd4 = "";
			if ("A".equals(upd3)) {
				if (upd2 == 4) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;",
							"卒業生",
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ?;", "卒業生",
							id1, id2);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
					model.addAttribute("class", upd);

					//最後の学籍番号と最後の学籍番号をそれぞれのMapにputする↓
					begin.put(id1, upd);
					last.put(id2, upd);

					session.setAttribute("map1", begin);
					session.setAttribute("map2", last);

					//session.setAttribute("kupd1", upd);
					//session.setAttribute("kid1", id1);
					//session.setAttribute("kid2", id2);
				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;", upd4,
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							upd4,
							id1, id2);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}
			} else if ("B".equals(upd3)) {
				if (upd2 == 3) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;",
							"卒業生",
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							"卒業生",
							id1, id2);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
					model.addAttribute("class", upd);

					//最後の学籍番号と最後の学籍番号をそれぞれのMapにputする↓
					begin.put(id1, upd);
					last.put(id2, upd);
					session.setAttribute("map1", begin);
					session.setAttribute("map2", last);

					//session.setAttribute("kupd1", upd);
					//session.setAttribute("kid1", id1);
					//session.setAttribute("kid2", id2);
				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;", upd4,
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							upd4,
							id1, id2);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}
			} else if ("C".equals(upd3) || "D".equals(upd3) || "N".equals(upd3)) {
				if (upd2 == 2) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;",
							"卒業生",
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							"卒業生",
							id1, id2);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());

					//最後の学籍番号と最後の学籍番号をそれぞれのMapにputする↓
					begin.put(id1, upd);
					last.put(id2, upd);

					session.setAttribute("map1", begin);
					session.setAttribute("map2", last);

					//session.setAttribute("kupd1", upd);
					//session.setAttribute("kid1", id1);
					//session.setAttribute("kid2", id2);
				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;", upd4,
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							upd4,
							id1, id2);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}

			} else {
				//何年制か分からないものは４年制として処理↓
				if (upd2 == 4) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;",
							"卒業生",
							upd, id1, id2);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							"卒業生",
							id1, id2);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
					model.addAttribute("class", upd);

					//最後の学籍番号と最後の学籍番号をそれぞれのMapにputする↓
					begin.put(id1, upd);
					last.put(id2, upd);

					session.setAttribute("map1", begin);
					session.setAttribute("map2", last);

					//session.setAttribute("kupd1", upd);
					//session.setAttribute("kid1", id1);
					//session.setAttribute("kid2", id2);
				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update("UPDATE m_user SET class  = ? WHERE class = ?;", upd4, upd);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
							upd4,
							id1, id2);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}
			}
			//卒業生が入力されたときの処理↓
		} else if ("卒業生".equals(upd)) {
			kensakukekka = jdbcTemplate.queryForList(
					"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;", "卒業生",
					id1, id2);
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
			return "mydbm_user";
		} else {
			//存在しないクラス等が入力されたときの処理↓
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user");
			model.addAttribute("kensakupra", kensakukekka);
			//そのクラスは存在しません。を表示するためのmodel↓
			model.addAttribute("no", "no");
			model.addAttribute("record", kensakukekka.size());
			return "mydbm_user";
		}

		//検索結果のリストをmodelに格納

		return "mydbm_user";
	}

	@RequestMapping(path = "/classreturn", method = RequestMethod.POST)
	public String dbfg5(HttpSession session, Model model, String returns, String id3, String id4) {
		//直前で卒業生にしてしまったクラス↓
		//		String keep1 = (String) session.getAttribute("kupd1");
		//卒業生にしてしまったクラスの最初の学籍番号↓
		//		String keepid1 = (String) session.getAttribute("kid1");
		//卒業生にしてしまったクラスの最後の学籍番号↓
		//		String keepid2 = (String) session.getAttribute("kid2");

		Map<String, String> map1 = (Map<String, String>) session.getAttribute("map1");
		Map<String, String> map2 = (Map<String, String>) session.getAttribute("map2");

		//それぞれのMapがnullじゃないときは保持↓
		if (map1 != null && map2 != null) {
			session.setAttribute("map1", map1);
			session.setAttribute("map2", map2);
		}

		List<Map<String, Object>> kensakukekka;

		kensakukekka = jdbcTemplate.queryForList(
				"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ?", returns, id3, id4);
		//存在するまたは何年制か知っているクラスの更新処理↓
		if (kensakukekka.size() != 0 && returns != null && !("卒業生".equals(returns)) && id3 != null && id4 != null) {

			//例)３Aの３を抽出し、int型にする処理 returns2↓
			int returns2 = Integer.parseInt(returns.substring(0, 1));
			//例)３AのAを抽出する処理 returns3↓
			String returns3 = returns.substring(1, 2);
			//最終的に上記２つを結合する変数 returns4↓
			String returns4 = "";
			if (returns2 == 1) {
				kensakukekka = jdbcTemplate.queryForList(
						"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id order by user_id;",
						returns, id3, id4);
				model.addAttribute("kensakupra", kensakukekka);
				model.addAttribute("record", kensakukekka.size());
				return "mydbm_user";
			} else {
				returns2 -= 1;
				returns4 += returns2 + returns3;
				jdbcTemplate.update("UPDATE m_user SET class = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;",
						returns4, returns,
						id3, id4);
				model.addAttribute("class", returns);
				kensakukekka = jdbcTemplate.queryForList(
						"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
						returns4, id3, id4);
				model.addAttribute("class", returns + "クラスになった" + returns4);
				model.addAttribute("kensakupra", kensakukekka);
				model.addAttribute("record", kensakukekka.size());
			}

			//卒業生が入力されたときの処理↓
		} else if ("卒業生".equals(returns) & map1 != null && map2 != null && map1.get(id3) != null
				&& map2.get(id4) != null && map1.size() != 0 && map2.size() != 0) {
			if (map1.get(id3).equals(map2.get(id4))) {
				jdbcTemplate.update("UPDATE m_user SET class = ? WHERE class = ? AND user_id >= ? AND user_id <= ?;",
						map1.get(id3), returns,
						id3, id4);
				kensakukekka = jdbcTemplate.queryForList(
						"SELECT * FROM m_user WHERE class = ? AND user_id >= ? AND user_id <= ? order by user_id;",
						map1.get(id3), id3,
						id4);
				model.addAttribute("kensakupra", kensakukekka);
				model.addAttribute("record", kensakukekka.size());
				model.addAttribute("class", returns + "になった" + map1.get(id3));
				map1.remove(id3);
				map2.remove(id4);
				session.setAttribute("map1", map1);
				session.setAttribute("map2", map2);
			} else {
				return "redirect:/classupd";
			}
		} else {
			//存在しないクラス等が入力されたときの処理↓
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user");
			model.addAttribute("kensakupra", kensakukekka);
			//そのクラスは存在しません。を表示するためのmodel↓
			model.addAttribute("no", "no");
			model.addAttribute("record", kensakukekka.size());
			return "mydbm_user";
		}

		//検索結果のリストをmodelに格納

		return "mydbm_user";
	}
}