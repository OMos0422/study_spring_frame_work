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

	@RequestMapping(path = "/classSet", method = RequestMethod.GET)
	public String dbfg7(Model model, HttpSession session) {
		String set = (String) session.getAttribute("setUp");
		if (set != null && "CD".equals(set)) {
			session.setAttribute("setUp", set);
			List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT class,class_CD FROM m_user\n"
					+ "group by class,class_CD order by class");
			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());

			return "mydbm_user";
		} else {
			return "mydbm_user";
		}
		//検索処理
	}

	@RequestMapping(path = "/classSet", method = RequestMethod.POST)
	public String dbfg9(Model model, HttpSession session, String cls, String cd) {
		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT class FROM m_user WHERE class = ?",
				cls);
		//検索結果のリストをmodelに格納
		if (kensakukekka.size() != 0) {
			jdbcTemplate.update("UPDATE m_user SET class_CD = ? WHERE class = ?", cd, cls);
			return "redirect:/classSet";
		}

		return "redirect:/classSet";
	}

	@RequestMapping(path = "/classSetSer", method = RequestMethod.POST)
	public String dbfg10(Model model, String cls) {
		//クラスが存在するか確認する処理↓
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList("SELECT class FROM m_user WHERE class = ?",
				cls);
		//1行のみ表示させる場合 例)１A１↓
		if (cls.length() == 3 && "1".equals(cls.substring(2, 3))) {
			String cls2 = cls.substring(0, 2);
			kensakukekka = jdbcTemplate.queryForList("SELECT class,class_CD FROM m_user WHERE class = ? limit 1", cls2);
			model.addAttribute("record", kensakukekka.size());
			model.addAttribute("kensakupra", kensakukekka);
			return "mydbm_user";
			//存在しないクラスを検索した場合や空白が入ってきた時の処理↓
		} else if (kensakukekka.size() == 0 || "".equals(cls) || cls == null) {
			return "redirect:/classSet";
		}
		kensakukekka = jdbcTemplate
				.queryForList("SELECT class,class_CD FROM m_user WHERE class = ?", cls);
		//検索結果のリストをmodelに格納
		model.addAttribute("record", kensakukekka.size());
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbm_user";
	}

	@RequestMapping(path = "/classlogout", method = RequestMethod.POST)
	public String dbfg8(Model model, HttpSession session) {
		//クラスCDセット画面から通常画面へ
		session.removeAttribute("setUp");
		return "redirect:/classupd";
	}

	@RequestMapping(path = "/classSer", method = RequestMethod.POST)
	public String dbfg2(Model model, HttpSession session, String cls) {

		//検索処理
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM m_user WHERE class = ?", cls);
		if (kensakukekka.size() != 0) {
			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);
			model.addAttribute("record", kensakukekka.size());
			//CDと入力するとクラスCDをセットする画面へ遷移↓
		} else if ("CD".equals(cls)) {
			session.setAttribute("setUp", cls);
			return "redirect:/classSet";
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

	//練習問題③用
	@RequestMapping(path = "/classupd", method = RequestMethod.POST)
	public String dbfg3(HttpSession session, Model model, String upd, String cd1) {

		List<Map<String, Object>> kensakukekka;
		Map<String, String> CD;
		//Mapを作成↓
		if (session.getAttribute("map") == null) {

			CD = new HashMap<>();
		} else {

			CD = (Map<String, String>) session.getAttribute("map");
		}

		kensakukekka = jdbcTemplate
				.queryForList("SELECT * FROM m_user WHERE class = ? AND class_CD = ?;", upd, cd1);

		//存在するまたは何年制か知っているクラスの更新処理↓
		if (kensakukekka.size() != 0 && upd != null && !("卒業生".equals(upd)) && cd1 != null) {
			//例)３Aの３を抽出し、int型にする処理 upd2↓

			int upd2 = Integer.parseInt(upd.substring(0, 1));
			//例)３AのAを抽出する処理 upd3↓
			String upd3 = upd.substring(1, 2);
			//最終的に上記２つを結合する変数 upd4↓
			String upd4 = "";
			if ("A".equals(upd3)) {
				if (upd2 == 4) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;",
							"卒業生", upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ?;", "卒業生",
							cd1);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
					model.addAttribute("class", upd);

					//クラスCDをキーにしたMapに更新元のクラス名をputする↓
					CD.put(cd1, upd);

					session.setAttribute("map", CD);

				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;", upd4,
							upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
							upd4,
							cd1);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}
			} else if ("B".equals(upd3)) {
				if (upd2 == 3) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;",
							"卒業生", upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ?;", "卒業生",
							cd1);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
					model.addAttribute("class", upd);

					//クラスCDをキーにしたMapに更新元のクラス名をputする↓
					CD.put(cd1, upd);

					session.setAttribute("map", CD);

				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;", upd4,
							upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
							upd4,
							cd1);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}
			} else if ("C".equals(upd3) || "D".equals(upd3) || "N".equals(upd3)) {
				if (upd2 == 2) {
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;",
							"卒業生", upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ?;", "卒業生",
							cd1);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());

					//クラスCDをキーにしたMapに更新元のクラス名をputする↓
					CD.put(cd1, upd);

					session.setAttribute("map", CD);

				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update(
							"UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;", upd4,
							upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
							upd4,
							cd1);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}

			} else {
				//何年制か分からないものは４年制として処理↓
				if (upd2 == 4) {
					jdbcTemplate.update("UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;",
							"卒業生", upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ?;", "卒業生",
							cd1);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
					model.addAttribute("class", upd);

					//クラスCDをキーにしたMapに更新元のクラス名をputする↓
					CD.put(cd1, upd);

					session.setAttribute("map", CD);

				} else {
					//抽出した数字に+１する処理↓
					upd2 += 1;
					//結合処理↓
					upd4 += upd2 + upd3;
					jdbcTemplate.update("UPDATE m_user SET class  = ? WHERE class = ? AND class_CD = ?;", upd4,
							upd, cd1);
					kensakukekka = jdbcTemplate.queryForList(
							"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
							upd4,
							cd1);
					model.addAttribute("class", upd);
					model.addAttribute("kensakupra", kensakukekka);
					model.addAttribute("record", kensakukekka.size());
				}
			}
			//卒業生が入力されたときの処理↓
		} else if ("卒業生".equals(upd)) {
			kensakukekka = jdbcTemplate.queryForList(
					"SELECT * FROM m_user WHERE class = ?;", "卒業生 order by user_id;");
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
	public String dbfg5(HttpSession session, Model model, String returns, String cd2) {
		//直前で卒業させたクラスが格納されている↓
		Map<String, String> map = (Map<String, String>) session.getAttribute("map");

		//Mapがnullじゃないときは保持↓
		if (map != null) {
			session.setAttribute("map", map);
		}

		List<Map<String, Object>> kensakukekka;

		kensakukekka = jdbcTemplate.queryForList(
				"SELECT * FROM m_user WHERE class = ? AND class_CD = ?", returns, cd2);
		//存在するまたは何年制か知っているクラスの更新処理↓
		if (kensakukekka.size() != 0 && returns != null && !("卒業生".equals(returns)) && cd2 != null) {

			//例)３Aの３を抽出し、int型にする処理 returns2↓
			int returns2 = Integer.parseInt(returns.substring(0, 1));
			//例)３AのAを抽出する処理 returns3↓
			String returns3 = returns.substring(1, 2);
			//最終的に上記２つを結合する変数 returns4↓
			String returns4 = "";
			if (returns2 == 1) {
				kensakukekka = jdbcTemplate.queryForList(
						"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
						returns, cd2);
				model.addAttribute("kensakupra", kensakukekka);
				model.addAttribute("record", kensakukekka.size());
				return "mydbm_user";
			} else {
				returns2 -= 1;
				returns4 += returns2 + returns3;
				jdbcTemplate.update("UPDATE m_user SET class = ? WHERE class = ? AND class_CD = ?;",
						returns4, returns, cd2);
				model.addAttribute("class", returns);
				kensakukekka = jdbcTemplate.queryForList(
						"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
						returns4, cd2);
				model.addAttribute("class", returns + "クラスになった" + returns4);
				model.addAttribute("kensakupra", kensakukekka);
				model.addAttribute("record", kensakukekka.size());
			}

			//卒業生が入力されたときの処理↓
		} else if ("卒業生".equals(returns)) {
			if (map != null && map.get(cd2) != null
					&& map.size() != 0) {
				jdbcTemplate.update("UPDATE m_user SET class = ? WHERE class = ? AND class_CD = ?;",
						map.get(cd2), returns, cd2);
				kensakukekka = jdbcTemplate.queryForList(
						"SELECT * FROM m_user WHERE class = ? AND class_CD = ? order by user_id;",
						map.get(cd2), cd2);
				model.addAttribute("kensakupra", kensakukekka);
				model.addAttribute("record", kensakukekka.size());
				model.addAttribute("class", returns + "になった" + map.get(cd2));
				map.remove(cd2);
				session.setAttribute("map", map);
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