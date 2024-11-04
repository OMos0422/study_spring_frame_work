package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.zukan;
import com.example.demo.repository.ZukanRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyDbPocketonController2 {

	@Autowired
	ZukanRepository z;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/pocke2", method = RequestMethod.GET)
	public String dbfg(Model model) {

		return "mydbpocketon2";
	}

	@RequestMapping(path = "/pocke2", method = RequestMethod.POST)
	public String dbfg0(HttpSession session, Model model, String poc, String pockename) {

		Random rand = new Random();

		int tp = rand.nextInt(4) + 0;
		int sb = rand.nextInt(2) + 0;
		int level = rand.nextInt(100) + 1;

		String[] type = { "草", "炎", "水", "地面" };
		String[] seibetsu = { "♂", "♀" };

		List<zukan> list = z.findAll();

		if ("図鑑".equals(poc) && list.size() != 0) {
			model.addAttribute("look", poc);
			model.addAttribute("list", list);
			session.removeAttribute("name");
			session.removeAttribute("type");
			session.removeAttribute("seibetsu");
			session.removeAttribute("level");
			session.removeAttribute("catch");
		} else if ("図鑑".equals(poc) && list.size() == 0) {
			session.removeAttribute("catch");
			model.addAttribute("no", "図鑑にはまだ何も登録されていません");
		}

		if (!("".equals(poc)) && "捕獲".equals(poc)) {
			session.setAttribute("catch", poc);
		}

		if (!("".equals(pockename)) && session.getAttribute("catch") != null) {
			session.setAttribute("name", pockename);
			session.setAttribute("type", type[tp]);
			session.setAttribute("seibetsu", seibetsu[sb]);
			String lv = Integer.toString(level);
			session.setAttribute("level", lv);
		}

		if (!("".equals(pockename)) && "名前変更".equals(poc)) {
			model.addAttribute("rename", poc);
		}

		if ("バトル".equals(poc) && list.size() > 1) {
			model.addAttribute("battle", poc);
			session.removeAttribute("catch");
		} else if ("バトル".equals(poc) && list.size() == 0) {
			model.addAttribute("nz", "まだポケトンを持っていません");
			session.removeAttribute("catch");
		} else if ("バトル".equals(poc) && list.size() == 1) {
			model.addAttribute("notexist", "ポケトンが1体しかいません");
			session.removeAttribute("catch");
		}

		if ("お別れ1".equals(poc) || "お別れ１".equals(poc)) {
			model.addAttribute("del1", poc);
			session.removeAttribute("catch");
		} else if ("お別れ2".equals(poc) || "お別れ２".equals(poc)) {
			model.addAttribute("del2", poc);
			session.removeAttribute("catch");
		}

		return "mydbpocketon2";
	}

	@RequestMapping(path = "/tr_name2", method = RequestMethod.POST)
	public String dbfg2(HttpSession session, String name) {
		if (!("".equals(name))) {
			session.setAttribute("tr_name", name);
		}
		return "redirect:/pocke2";

	}

	@RequestMapping(path = "/catch2", method = RequestMethod.POST)
	public String dbfg4(Model model, HttpSession session, String ch, String ball) {

		zukan zukan = new zukan();

		String name = (String) session.getAttribute("name");
		String type = (String) session.getAttribute("type");
		String seibetsu = (String) session.getAttribute("seibetsu");
		String level = (String) session.getAttribute("level");

		Random rand = new Random();

		boolean flag = false;

		int r = rand.nextInt(100) + 1;

		String b = "";

		List<zukan> list = z.findAll();

		int no = 0;

		if (list.size() >= 1) {
			no = (int) list.get(list.size() - 1).getNo() + 1;
		} else {
			no = 1;
		}

		String[] balls = { "マスターボール", "モンスターボール", "スーパーボール", "ハイパーボール" };

		if (balls[0].equals(ball)) {
			flag = true;
		} else if (balls[1].equals(ball) && r >= 70) {
			flag = true;
		} else if (balls[2].equals(ball) && r >= 50) {
			flag = true;
		} else if (balls[3].equals(ball) && r >= 35) {
			flag = true;
		} else if (!(balls[0].equals(ball)) && !(balls[1].equals(ball)) && !(balls[2].equals(ball))
				&& !(balls[3].equals(ball))) {
			b = "no";
			model.addAttribute("nb", "そのボールは使えません");
		}

		if ("catch".equals(ch) && !("".equals(name)) && name != null && type != null && seibetsu != null
				&& level != null && flag == true) {

			zukan.setNo(no);
			zukan.setName(name);
			zukan.setType(type);
			zukan.setSeibetsu(seibetsu);
			zukan.setLevel(level);
			zukan.setBall(ball);

			z.save(zukan);

			model.addAttribute("ok", "捕獲に成功しました！");
		} else if (flag == false && ("".equals(b))) {
			model.addAttribute("miss", "逃げられてしまった......");
		}

		return "mydbpocketon2";

	}

	@RequestMapping(path = "/rename2", method = RequestMethod.POST)
	public String rename(Model model, HttpSession session, String no, String rn) {

		zukan zukan = new zukan();

		if ("".equals(no) || "".equals(rn) || no != null && rn != null) {
			return "mypocketon2";
		}

		int n = Integer.parseInt(no);

		List<zukan> list = z.findByNo(n);

		if (list.size() != 0) {

			zukan.setNo(n);
			zukan.setName(rn);

			z.save(zukan);

			model.addAttribute("suc", "No" + no + "の" + "名前が変更されました");
		} else {
			model.addAttribute("m", "エラー");
		}

		return "mydbpocketon2";

	}

	@RequestMapping(path = "/battle2", method = RequestMethod.POST)
	public String dbfg5(HttpSession session, Model model, String pocke1, String pocke2) {

		if ("".equals(pocke1) || "".equals(pocke2)) {
			model.addAttribute("e", "ポケトンのNoを2体分入力してください");
			return "mydbpocketon2";
		}

		int p1 = Integer.parseInt(pocke1);
		int p2 = Integer.parseInt(pocke2);

		List<zukan> list = z.findByNo(p1);
		List<zukan> list2 = z.findByNo(p2);

		int no = (int) list.get(0).getNo();
		int no2 = (int) list2.get(0).getNo();

		String le = (String) list.get(0).getLevel();
		String le2 = (String) list2.get(0).getLevel();

		int l = Integer.parseInt(le);
		int l2 = Integer.parseInt(le2);

		String n = (String) list.get(0).getName();
		String n2 = (String) list2.get(0).getName();

		String t = (String) list2.get(0).getName();
		String t2 = (String) list2.get(0).getName();

		if (list.size() == 1 && list2.size() == 1) {

			if ("炎".equals(t)) {

				if ("草".equals(t2)) {
					l *= 2;

				} else if ("水".equals(t2)) {
					l2 *= 2;
				}
			} else if ("草".equals(t)) {

				if ("地面".equals(t2)) {
					l *= 2;

				} else if ("炎".equals(t2)) {
					l2 *= 2;
				}

			} else if ("地面".equals(t)) {

				if ("水".equals(t2)) {
					l *= 2;

				} else if ("草".equals(t2)) {
					l2 *= 2;
				}

			} else if ("水".equals(t)) {

				if ("炎".equals(t2)) {
					l *= 2;

				} else if ("地面".equals(t2)) {
					l2 *= 2;
				}
			}

			if (l > l2) {
				model.addAttribute("winner", "勝ったのは" + "No" + no + "の" + "”" + n + "”");
			} else if (l < l2) {
				model.addAttribute("winner", "勝ったのは" + "No" + no2 + "の" + "”" + n2 + "”");
			} else {
				model.addAttribute("draw", "引き分け");
			}

		}

		return "mydbpocketon2";
	}

	@RequestMapping(path = "/pockeDel3", method = RequestMethod.POST)
	public String dbfg6(HttpSession session, Model model, String del1) {

		List<zukan> list = z.findAll();

		if (list.size() != 0 && "はい".equals(del1)) {
			z.deleteAll();
			model.addAttribute("delete", "すべてのポケトンとお別れしました");
		} else if (list.size() == 0) {
			model.addAttribute("nots", "捕獲済みのポケトンはいません");
		} else if (!("はい".equals(del1))) {
			model.addAttribute("error", "エラー");
		}
		return "mydbpocketon2";
	}

	@RequestMapping(path = "/pockeDel4", method = RequestMethod.POST)
	public String dbfg7(HttpSession session, Model model, String del2) {

		zukan zukan = new zukan();

		if ("".equals(del2)) {
			return "mypocketon2";
		}

		int d2 = Integer.parseInt(del2);

		List<zukan> list = z.findByNo(d2);

		String d = (String) list.get(0).getName();

		if (list.size() != 0 && !("".equals(del2)) && del2 != null) {

			zukan.setNo(d2);

			z.delete(zukan);

			model.addAttribute("delete2", "No" + del2 + "の" + "”" + d + "”" + "とお別れしました");
		} else if (list.size() == 0 && !("".equals(del2)) && del2 != null) {
			model.addAttribute("no2", "そのポケトンは存在しません");
		}
		return "mydbpocketon2";
	}

}