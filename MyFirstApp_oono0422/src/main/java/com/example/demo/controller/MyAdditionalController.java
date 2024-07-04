package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAdditionalController {
	@RequestMapping(path = "/additional", method = RequestMethod.GET)
	public String firstget() {
		return "myadditional";
	}

	@RequestMapping(path = "/additional", method = RequestMethod.POST)
	public String firstpost(Model model, String q1, String q2, String q3, String q4) {
		String kekka = "";
		if ("i".equals(q1) && "s".equals(q2) && "t".equals(q3) && "j".equals(q4)) {
			kekka = "ISTJ（「義務遂行者」）:\n"
					+ "実用的で真面目、事実に基づいて行動する。組織と責任を重んじる。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "s".equals(q2) && "f".equals(q3) && "j".equals(q4)) {
			kekka = "ISFJ（「保護者」）:\n"
					+ "温かく、寛大、忠実。伝統と安定性を大切にする。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "n".equals(q2) && "f".equals(q3) && "j".equals(q4)) {
			kekka = "INFJ（「カウンセラー」）:\n"
					+ "洞察力があり、直感的。理想主義的で、他人の成長を助けることに情熱を持つ。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "n".equals(q2) && "t".equals(q3) && "j".equals(q4)) {
			kekka = "INTJ（「戦略家」）:\n"
					+ "独立心が強く、創造的。複雑な問題解決に優れている。";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "s".equals(q2) && "t".equals(q3) && "p".equals(q4)) {
			kekka = "ISTP（「職人」）:\n"
					+ "現実的で柔軟、効率的。危機管理能力が高い。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "s".equals(q2) && "f".equals(q3) && "p".equals(q4)) {
			kekka = "ISFP（「アーティスト」）:\n"
					+ "穏やかで忍耐強い。現在を生きることを楽しむ。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "n".equals(q2) && "f".equals(q3) && "p".equals(q4)) {
			kekka = "INFP（「仲介者」）:\n"
					+ "内省的で理想主義的。創造的で情熱的。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("i".equals(q1) && "n".equals(q2) && "t".equals(q3) && "p".equals(q4)) {
			kekka = "INTP（「論理学者」）:\n"
					+ "非常に知的で理論的。好奇心旺盛で独創的。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "s".equals(q2) && "t".equals(q3) && "p".equals(q4)) {
			kekka = "ESTP（「冒険家」）:\n"
					+ "行動的でエネルギッシュ。現実的で解決志向。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "s".equals(q2) && "f".equals(q3) && "p".equals(q4)) {
			kekka = "ESFP（「パフォーマー」）:\n"
					+ "社交的で活発。楽しいことを見つけ、共有するのが得意。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "n".equals(q2) && "f".equals(q3) && "p".equals(q4)) {
			kekka = "ENFP（「活動家」）:\n"
					+ "熱意があり、創造的。可能性を見つけて刺激する。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "n".equals(q2) && "t".equals(q3) && "p".equals(q4)) {
			kekka = "ENTP（「発明家」）:\n"
					+ "知的で好奇心が強い。新しいことに挑戦するのが好き。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "s".equals(q2) && "t".equals(q3) && "j".equals(q4)) {
			kekka = "ESTJ（「実行者」）:\n"
					+ "組織的で実践的。リーダーシップがあり、物事を成し遂げる。";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "s".equals(q2) && "f".equals(q3) && "j".equals(q4)) {
			kekka = "ESFJ（「提供者」）:\n"
					+ "協力的で調和を重んじる。他人のニーズに敏感。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "n".equals(q2) && "f".equals(q3) && "j".equals(q4)) {
			kekka = "ENFJ（「教師」）:\n"
					+ "社交的でカリスマ的。他人を奮い立たせ、導く。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else if ("e".equals(q1) && "n".equals(q2) && "t".equals(q3) && "j".equals(q4)) {
			kekka = "ENTJ（「指導者」）:\n"
					+ "決断力があり、自信がある。挑戦を楽しみ、目標を達成する。\n"
					+ "";
			model.addAttribute("kekka", kekka);
			return "myadditional";
		} else {
			return "myadditional";
		}

	}

}
