package pl.poblock.model.response;

public class Podroz {
	private Loty podrozTam;
	private Loty podrozPowrot;
	private Double cenaRazem;
	public Podroz(Loty podrozTam, Loty podrozPowrot) {
		this.podrozTam = podrozTam;
		this.podrozPowrot = podrozPowrot;
		setCenaRazem(podrozTam.getSuma()+podrozPowrot.getSuma());
	}
	public Loty getPodrozTam() {
		return podrozTam;
	}
	public void setPodrozTam(Loty podrozTam) {
		this.podrozTam = podrozTam;
	}
	public Loty getPodrozPowrot() {
		return podrozPowrot;
	}
	public void setPodrozPowrot(Loty podrozPowrot) {
		this.podrozPowrot = podrozPowrot;
	}
	public Double getCenaRazem() {
		return cenaRazem;
	}
	public void setCenaRazem(Double cenaRazem) {
		this.cenaRazem = cenaRazem;
	}
	@Override
	public String toString() {
		return "Podroz [podrozTam=" + podrozTam + ", podrozPowrot=" + podrozPowrot + ", cenaRazem=" + cenaRazem + "]";
	}
}
