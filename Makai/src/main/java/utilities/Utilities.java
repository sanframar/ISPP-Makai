package utilities;

import org.springframework.security.crypto.codec.Base64;

public class Utilities {

	public static String showImage(final byte[] picture) {

		String result;
		byte[] base64;
		StringBuilder imageString;
		imageString = new StringBuilder();
		imageString.append("data:image/png;base64,");

		if (picture.length != 0) {
			base64 = Base64.encode(picture);
			imageString.append(new String(base64));
			result = imageString.toString();
		} else {
			imageString.append("iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAG7AAABuwBHnU4NQAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAACAASURBVHic7Z13XFPn98c/CUlIIOwtQxBwAIJbQMRdrXvj1rpnHYiAtf221dZt7dLW1u7WVWerdQ8QsRarbNkgsvcIJATI7w+LP2sdwPMk9ybk/Xrdl4zc8xxizrnPOIOjUCigRX0JDgnlSaVSX4lE4i+VStvL5XKjfy4DubxOr05Wpy+rk4lkUplQKpMKpLW1/FqplFdTU6MjqZZwAEBfrK/Q09NrEAmF9UKRSC7UFdbpCnWlugLdWoGuQMLnC2r4fH4Vn8+v4PP5FUKhMEtfXz9MKBTe2r5taz3T74GW1sPROgD1IDBog32NRDK0urq6b0VFuUdJSWn7goJ880fZ2UKZTMaITrq6urCzt5daWVkXm5mZZhkZGceJxeI/9fT1L+/euSObEaW0tAitA2AZG0JChZWVlbNKiovHlJWVdiwqKrLJzckxKC4u5jKtW0swNzdvbGdrW2VhYZFnYmKabGZu/puhoeFPO7ZtlTKtm5b/R+sAGCY4JJRXVV09paiwYHpWVpZ3QkKCuaS6msO0XspAXyxWuLm5Fbdv3/62haXVIQOx+Jh2CcEsWgfAACvfXD2mqLBwdnZ2dr/EhHib8vJyjTT4V2FsbKzo4uaeZ29vH2FhafnjZ598/BvTOrU1tA5ABaxdF9itoKBg3aNH2f6JiYn2xUVFajWdVxXmFhaNXbp0ybazsw+zsrLa89Ge3feZ1knT0ToAJRG0Idi8oKDg3YSE+ID79+6ZNzQ0MK2SWqGjo4Nu3bsXu7m5H7Gysnp3547txUzrpIloHQBFgkNCuWVlZatTU1KW3/nrjrOmruVVjb5YrOjTu0+ai6vrPhMTk4+3b9vayLROmoLWAVBg1eo1IzIzMt6+G/VXn7y8PB7T+mgyNjY29T179b7j6OS0+dOP955nWh91R+sAWsm69UGuuTk5W2JiokclJiToM61PW6SLm5vE09PrbDtb2017du1MYVofdUTrAFrIm2vWDoqPi9sfHhbWSS6vY1odLQD4fAH6+/snuXt4LPtk70fXmNZHndA6gGayctWb42JjYvZGRNx01G7osRMdHR306+eX2dXTc81nn35ymml91AGtA3gFy1esnH3v3r3tf96OtNG+V+oBh8NBX2+fvO7duwfv+/yzH5nWh9UoFArt9Zxr8ZKlq3r27FUMQKG91Pfq2bNX8eIlS1cx/Xli68W4Amy7FixctKmrp2cF0x9c7UX36urpWbFg4aJNTH++2HYxrgBbroWLlwR26tRZwvQHVXsp9+rUqbNk4eIlgUx/3thytfk9gLWB6z3/joo6HRZ2w5FpXUiwsLSEi4srnJ2dYWRsDLFYDAMDA4jFYojFBhAbNH1v8K/fAUB1dTWqqqpQXV2N6uqqx19XPf766d9VlJcjNTUVqakpKC4qYvgvJsPff0Bmj169xn20e1cM07owSZt1ABtCQgVZmZk/nzt7dlJ1dZVaROwJhSI4O3eAi6srXFxcn/zr7OICQ0NDlepSXl6O1NQUpCQnIzUlBSkpKUhNSUFGRjrkcrlKdWktYrGBYuSoUcfbOzrO3LFta5s8022TDmDZ8hXzr1698llyUpKIaV1eBI/HQ/fuPeDXvz+8fX3RqVNn2NragsNht6+qr6/Hw6wsPHiQiNuRkQgLu4H4uDiw+XPWsVOn2sGDh6zcv+/zb5jWRdW0KQcQuD7IOTo6+rerVy53YdvfzeVy0dXTE379/dHf3x8+Pr7Q19eMAMOysjJE3LyJ8LAbuBkejqSkB0yr9B84HA4GDxma6OXlNWb3rp1pTOujKtqEAwgOCeU+evToqz/OnZtXVlbKilRcDoeDLl3c4Ne/P/r7+8O3nx+MjIyYVkslFBYW4GZ4OMLDwhAeHobMjAymVXqCiYlp4+sjR35nZ2e3qC0kHWm8A1i56s2JN25c/zYuNla1i+QX0KlTZwRMm47JU6eiXbt2TKvDCtLT0nD0yGEcPXIYDx8+ZFodAIBH166VAwYMfOOzTz85wbQuykSjHUDAtOlHT544MYXpmH0zMzNMnDQZAdOmo1v37ozqwmYUCgUib0XgyOHDOHP6FKqqqhjVh88XYMLEiceOHD40lVFFlIhGOoDAoA3tI2/duhV5K4KxR6xAIMDwESMQMG06hgwdBj6fz5QqaolUWotzZ8/iyKFDuH79GpjMv/Dx7Zfr4+vru3vnjizGlFASGucAlq1YOfe306e/zsl5xEhefo+ePTF9xkxMmDgJxsbGTKigcRQU5OP4sWP45eef8eBBIiM62Nra1Y8ZN27h/s8/+54RBZSERjmAKVMDTp0+dXIcE+fQAwYOROD6IPj281P52G2JSxcvYPfOnYiK+kvlY/P5fIwbP+H0saNHxqt8cCWhEQ4gcH2QU0TEzVt/3r5trcpxORwOXhs+AusC16Nnr16qHLrNEx4Wht27duBmeLjKx+7r7Z3fr5+f7+5dO9lzfNFK1N4BLF22fNGZ06f2qbIUF5fLxdhx47A2MAju7u6qGlbLc/jrrzvYvXMnLl+6qNJxbWxs6seOG7/8i/37vlLpwJRRWwcQHBLKTUtLO3P61MlR9fWq6S3B4/EwecpUrFm7Di6urioZU0vziI2JwZ7dO/H7b7+pLOqQx+Nh3PgJZ52dnceqa8yAWjqAoOAQ44ibN+NVtcvP4XAwY+YsBAZtgIODgyqG1NJKkpOTsH3rhzh96pTKxvTx7Zfbz8/Pfef2beUqG5QSaucAAoM22F+5dCkmOvq+SrbYPb28sHPXHu0aX80Iu3EDwUHrkZKSrJLxvLy6lQ8ZNsxT3ZqiqpUDWBu43vPsb7/dTklJVnoSj5GRETZuehtvzF8ALpcV0cNaWkhdXR327/scu3ZsR21trdLHc3XtWDtqzBhvdUoxVhsH8ObqNUNOHD9+XhXn+wHTpuO99zfD3MJC2UMpjcbGRqRlZCI5ORlp6enIzMxCbu4j5Ofno7S4GBXlZaiVVIPL1YGuUAihSA96+vrQ1xdDbGAAIyNjdO7SBf7+fujv6wuBQMD0n9RqHj16hLdCQ3D2d+W3HrS1taufOGnSiE8+3ntF6YNRQC0cwPKVK2ccPXz4x5KSEqU+it3c3LFj1y54+/gqcxil0NjYiEtXruLsuXP483YkstJSUU8pBFpHhwdzK2t0cHWFl1c3DBwwAEMGDWR9avKzXL58CSEbgpSefGRmZtY4ddq02fs+++wXpQ5EAdY7gEVLlgYe+uXnncpssyUWixEcuhGLFi8Bj6cejX0UCgXCIiJw5rffcfvWLWSkJKFOJlXZ+PqGRujj0w8zZkzHuNGj1WaZVCeT4eOP92Lvnj2QKfH90heLFdNnzAz66ssvdittEAqw2gHMe2P+rkO//BxYV6e8ZB5vbx8cOPiN2mTmhUdEYvee3Yi6fQvSGgnT6gAA9MQG6OXti2kBAZg8cYJaOIP0tDQsmD8PsTHKW64LBAJMnzFz93fffrNeaYMQwloHMG3GjEPHjhyZ1tionONVDoeD1WvWIvStTdDR0VHKGLSQyerw2Rdf4Kfvv0N2BrtrVYiNjBEwYxbe3rgRYjG7C5rUyWR4a2Movv3moNLG4HK5mBIQcPjwL79MV9ogBLDSAUycNPniyRPHhylLvpm5OfZ/cQCDhwxR1hBUSHyQhG07duDqxfOolVQzrU6LEIr0MGr8JGx573+wYPlm6qmTJ7F29Sqlph9PmDjp0onjv76mtAFaCescwLQZMw4dOXRomrLk+/bzw4Gvv4a1tY2yhiDm7B8XsHXrh0iKj4FCSTMgVcEXCDBo2Ahs3vwenJ06MK3OC8lIT8f8N+YqdUkQMH0662YCrHIA896Yv+vHH74PVMa0n8vlYl3gegQFh7B2yp/18CGWrliBqIhwVhfRbA0cLhdWNnbo2s0LAwYMxPixo2FtpdLcrVei7CUBl8vF7DlzWbUnwBoHsGjJ0sAfvvt2lzI2/CwsLfHlga/hP2AAddk0kNfXI+StTTj0/bcq3clnEg6HAxMLSzi7dISTcwd07tQZ3by80KdXTwiFQkZ1U+aSQCAQYM68N9az5XSAFQ5g+cqVM374/vuflHHU5+3tg2++/x6Wlla0RVPh8LFf8c6mjSgtLGBaFVbA4wvg5tkN4ydOwBtz5sDgn+YlqiYjPR1zZ89CQkI8ddn6YrFizty5s9gQJ8C4A3hz9Zohv/z800VlBPm8PnIUvv7mG+jqMvtEeR6JD5KwfMUKxP6t+sIW6gKPz0dfP3/s2L4DnVxdVD5+ZWUlZk6fhshbEdRlm5mZNc6YOes1piMGGT2wXRu43vPE8ePnlWH8M2fNxnc//MhK4/9k334MHuCnNf5XUC+XI+LaFfj79kXAjJnIfvRIpeMbGhri1xMnMHLUaOqyS0pKuCeOHz+/NnC9J3XhLUDn3XffZWTgwKAN9r+fOROdmZmhS1v26jVrsXX7DtYFpDQ0NGDu/AU4uO9TNDJY5FLdUDQ2IiM1Gd8cPAgdoQg+ffuobGwej4ex48ajID8fMdHRVGVXVVVyS4pL5mU+zP5h+GvDKqkKbyaMLAGCgkOML124kEE7pZfD4eD9LR9g2fIVNMVSITcvD+PHT0B6MjNFLTUJ/yGv4ecffoBIT7Wd3T7cshl7du+iLtfLq1v5sOHDnZioJ6ByBxAcEsoNDw/Ppl3Mg8fj4dPP92HK1ACaYqlw5dp1LHxjHqoqyphWRWOwtLHF5StX0c5GtUeJXx34EhtDgqkf0/r49svt37+/vaorC6l8jpyWlnaGtvGLRCL89MshVhr/1p27MGPqZK3xU6YwLwczZs1U+biLFi/Bga8PUk+PjrwV0S4tLe0MVaHNQKUzgKXLli86+PVXB2jW8DM2Nsaho8fQu7fq1oXNZdrMWbh8Tvk56C9DIBTByMwCQj0xdPX0IdTT/+dfPeiK9AEoIJfJUCeTQi6TQV4nhaSiHFVlJagsLYastoZR/V/F+o1vIyRI9XE1169dw5xZM1BTQ+/94fF4WLBw0WJVFhpVmQMIXB/kdOiXn5NpVu8ViUQ4cfoMK41/5py5uPCb6urSNcHXFcLE0gamVjYwsbKB2MiESF6dtBYVJUUoznmIwuxM1gUq8XV1ceV6GNw6d1b52NevXcOMaVNBM3jNxsamfvqMmR1VVXJcZQ7A28cnj2bdfh6Ph59+OYShw1iXX4HFy5bjxOGfVTaerkgPts6dYOXgBLGxqdLGUSgUKCvIRf7DDBRmZ0AukyltrJbg4OyKqDt3GDn1OXniOBYvXEB1T6Cvt3f+7chIlSSrqOQYcMrUgFOXLl6gVlWTw+Hg8/1fYMzYcbREUmNt0AYc+eFblYxlZm2Ljt37okuffjC1toVAqNxdcQ6HA5HYEBa2DnDo5AFdkR6qK8qoVR5qLRVlpcgrLsOI4ap/GHTp4gYTExNcuXyJmsycR4/E8QmJ3adOmXKYmtAXoHQHsGzFyrmHfvk5hGaCz+YPPsTceW9Qk0eLd97bjIP7PlXqGDw+H/ad3OHhMxAOnT2gb2TMSGkuDpcLIzMLOHR0g1DfAJLKMsjrmJsRxMXcx9jxE2FuZqbysXv27IX6+npERt6iJjMlJblzbn5B5uhRI+kGHzyDUh1AYNCG9id+/fVyeXk5tbnZ6jVrERi0gZY4auzY8xE+3rlVqWPYOLmi+4DXYGnvCL4u9fipVsHhcGBoagb7jm4wNDVHQ0MDaquqAKj2eFmhUCC3sAgTJzDTtq+//wDk5eVRCxZqbGxEXm7e6Jy8/B+GvzasgorQ56DUPQDffn45NI/8Zs6ajY8//YyWOGrs//IrvLNxg9Jy9w1NzdG5ly+MzC2VIp82ddJa5KYnIyctGTVVSvvs/geBUITklDTGKhE1NDTgjblzcO7s79Rk+vj2y70VcdOWmsBnUJoDCJg2/ejRI4en0JL3+shR+O6HH1mXyx919x5Gvz4M9UroSCzQFcKlW2+069BR7SrwNiGpKEdlafGTq6qsGA1KbOW2bPU6bH73f0qT/ypkMikmT5xINYFoasC0Y0cOH5pKTeBTKMUBrFz15sQDX355XE5pc8jb2wfHT51iXWKPTCZDjx49UJBLP0nF1KoduvoNhoBlfzMpCoUCNZUVKC/KR3lxAcoKC1BbTS8M3sTcAikpqdTktYbKykqMfn0EtVRiPl+AxUuWTPrs009OUBH4FNQdQHBIKPfcubNlcbGxhjTkWVha4kb4TVbm88+aOw/nz5ykLtfRzQsuXr3U9qnfUuqktSgvykfWgziUF5HXRdjz6T7MYSBK8Gky0tMxeKA/taIiHl27Vo4cOcqEdqgw9YPTR48efUXL+LlcLr488DUrjf/wsV+pB/rw+AJ4+Q+Da7febcb4gcdrd0t7J3Qb8BpEYgNiefv376egFRlOHTrgo4/pnQjFxcYaPnr0iHqEIFUHELg+yPmPc+fm0ZK3LnA9K8t45RfkY0PgWqrBH2JjU/QdMQ6Wdu2pyVQ3+AJdePUfSiwnI+UBGliQbj1+wgS8MX8BNXl/nDs3L3B9kDM1gaDsAKKjo38rKyulItO3nx+CgkNoiKJOwLTpqKmit241NDVH72GjoWdgRE2mumJgYgbzdvZEMurlcoTdpHcmT8IHH25FV086NT/Kykq50dHRVJNLqDmAZctXzL965XIXGrLMzM1x4OuvWbfjDwDvbfkA8ff/piZPbGSCHoNeB4+vvs03aePQ2YNYxtVrVyloQo5AVxfffPs9DAzIlzYAcPXK5S7Llq+YT0UYKDmADSGhgqtXr3xGY0rM4XCw/4sDrKzb/ygnB/s/2UtNnp6BEXoOGcmaoB62YGZtC6Ee2Vl+1F/sKbdGcz9AoVDg6tUrn20ICaXyxKDiALIyM39OTkqiEoi+es1a1nbsCdoQQi3uXaRvgJ5DRio9fl9dMTQj6yaUmpxESRM60NwPSE5KEmVlZlLJNiN2AGsD13ueO3t2Eg1lvL19EPrWJhqiqJOWkYGrF/+gIksgFKHnkJHETzlNxsCELKa/vKQIRcUllLShA839gHNnz06iUVCU2AH8HRV1urq6ivjMSiwW48DBb1i57geADcEhaKinE+3n4TOAynGXJmNoak50v0KhQFh4OCVt6CDQ1cXBb76jEtBWXV3F+Tsq6jSpHCIHsGjJ0sCwsBuOpEoAQHDoRta26E58kISbVy9TkdW+c1eY2dhRkaXJkM4AAEAiYUf79Kfp4OyMNevWUZEVFnbDcdGSpYEkMogcQPiNG++T3N+Em5s7Fi1eQkOUUtgQEoKGBvL4dUNTc7h0601BI81HV6RHHAxVz4JYgOexevUaODo5UZFFaoOtdgALFy3elJT0QI9k8CZ27NoFHo9apTCq3I+JxZ/h14nl6PD46NpvEOt6FbAZHT6f6H65EhK0aCDQ1cW2HTupyEpKeqC3cNHiVm+ctfrTeOfOn0GtvfdpAqZNh7ePLw1RSiEkJAQ0ipl07uWjDfRpIaSxEcrMOiRl6NBhGDV6DBVZJLbYKgewZOmyVbExMcTx/kZGRnjv/c2kYpRGSWkp7v5JHlFmZG6Jdh06UtCobcEnLL0tZ7EDAIAPtm6DSER+DBwbE2O4ZOmyVa25t1UO4G5UFJWE642b3oa5Bdl5rzK5fPUalSIfrtp1f6sgnQHU17NzD6AJOzs7rN8QTEVWa22yxQ5g+YqVs+/ejSLeovX08qKaKKEMIm6RP/3NbOxgYsm+qEZ1gNQBSFne0wAAli1fAVdX8tnh3btRZstXrJzd0vta7ADu3bu3vaX3PAuHw8HOXXtYvyEWFxNDLEP79G89pA4gPUMlpfWJEAgE2L6TTr/B1thmiyxw5ao3x/15m7xe+YyZs9CzF7Uq4UojKyON6H7r9h2onGe3VYT6ZJGSWZmZdBRRMv4DBmDcePJipn/ejrRZuerNFtXKb5EDiI2J2Uua8MPj8VhZ1fdZiktKUFlWSiTD2bMnJW3aJiIx2T5zfl4uJU2UT3DoRuK4B4VCgdiYmBZlqzXbAby5Zu2giIibji3W6hkmT5kKBwcHUjFK5/LVa0QFP3gCgfbYjxA9QgdQVlxESRPl07FjJ4weQ34sGBFx0/HNNWsHNff1zXYA8XFx+0mrrHC5XKxZSycMUtn8RZhOKtQTU9Kk7SIyIHMAMmktCgoKKWmjfNYFkofWNDQ0ID4urtk10ZrlANatD3INDwvr1Hq1HjN23Di4uLqSilEJ2dnZRPeL9LUOgBShnj64OmQRonfv3aOkjfLp6ulJpddleFhYp3Xrg5plaM1yALk5OVtIS3xzOByspeDhVEVBfj7R/doZAB30CLMmY+PiKGmiGgKDyG1ELq9Dbk7Olua8tlkOICYmehSZSsBrw0fA3d2dVIzKKC0pJrqfdAdby2NIlwHJySmUNFENvXv3gV///sRymmuzr3QAq1avGZGYkED8aV4XuJ5UhEqpKC8jul+oXQJQQY/QATzMYn8swLMEric/JUtMSNBftXrNiFe97pUOIDMj421SZQYMHKgW5/5N1NXJUVtdTSRDuwSgA+lJQF6u+hwFNtHf3x+9epEHkDXHdl/qAIJDQrl3o/7qQ6pI4Hr1WfsDQHJqChQKshwA7QyADqQzgDLCpRxT0NgLuBv1V5/gkNCX2vhLf1lWVrY6Ly+PaBu2R8+e8O3nRyJC5aSkkkUAcjhc6IqolEpo8xAfBdbWqNVRYBPDXhuOzp3Jquzn5eXxysrKVr/sNS91AKkpKcuJNAAwfQazPdpaQ2UlWdMPXT3yajZaHiPUExPnjNy9f5+SNqplxkxy23mVDb/wnQ3aEGx+5687RG2IBAIBJkykUjBYpVRLyNb/2hgAenA4HOICqrGx6nUU2MSkKVOIi+Te+euOc9CG4BdWWH2hAygoKHhXUl1N9BgbPmIEjI2NSUQwgqSarJgkT6Bt9EET0pyAlJRkSpqoFisrawwc2Oyo3uciqa7mFBQUvPui37/QASQkxAcQjYzH5b7UEUkNYR455ZbrbR3SnArSqE4mCZhObkMvs+XnOoC16wK73b93j6gwu5mZGYYMHUYigjFqashmADS7BmshPwmQ1tZS0kT1jBw1iriv4P1798zXrgvs9rzfPdcBFBQUrCNN/Jk4aTL4hFVdmaKmhuwDQ3qEqOXfkHZQqquj086NCYRCEcaOI6sV0NDQgIKCgudm4T3XATx6lO1PNCLUd/oPkD8xtDMAunAJN8LqWVoevLkETJtGLONFNv1cB5CYmEjUoL1Tp87o1r07iQhGIZ65aB0AVbhcMgdAmsjGND6+/YhraLzIpv/jAFa+uXpMcVER0cGrOj/9AUCfMJFHOwOgC/EMgOXlwV8Fh8PB1ACyWUBxURF35Zur/1Nx5D+GXlRY2OLKok/D4XAweepUEhGMIxaTneNrHQBd2roDAEDsAIDn2/Z/HEB2dnY/kkG6dHFjbZPP5qJ1AOyCdAmg7nsAwOOmoqT9BJ9n2/9yAMEhobzEhHiiqr80cpmZhvTYResA6MLVIQsFptHYlQ3070+2N5+YEG8THBL6r9yef72zVdXVU8rLy4mi//r7Ex8gMA6pA9BuAtKFQzgDYHOPwJZAalvl5eWcqurqKU//7F8OoKiwgGj3jsvlql3m3/MwNCQLPNHOAOhCugdAGtPCFmjMrp+18X85gKysLG8S4V09PWFkpP6lsMnrs2sDgWhCugfQqCFLAEtLK3Tq1JlIxrM2/sQBbAgJFSYkJBCF//oRrlHYgkRCGArcqJ0B0IR8D0AzZgAA+SwgISHBfENIqLDp+yfvbGVl5SzS7D9NWP8DQA2pA9DOAKhCPgNo0JhlWX//AUT3S6qrOZWVlbOavn/iAEqKi4nakvB4PPj4+JKIYA3VErJsQO0MgD6kTqCWML+DLfTz8yNeoj5t608cQFlZKVGP4u7dexBH0LGFGsK20toZAH1IlwESwgxPtmBiYgJ3Dw8iGU/b+pN3taioqM2f/zdRXVVFdL+mTDfZBOkMoLKa7P+UTfgTLgOetvUnDiA3J4fo8NvbVzOm/wBw+fIlovu1BUHpo0uYEvzNt99T0oR5vH18iO5/2ta5ABAYtMG+uLiYaI5FejzBFmLi4pGaEE8kQ9sVmD6kRUGO/PITJU2Yh7RacHFxMTcwaIM98I8DqJFIhpIIFApFsLW1JVKKLWzfsYN4Da9nqHUAtCF1AOUlxTj4nWbMAhzatydOWW+yeS4AVFdX9yUR5uzcQSPKYEtqanDj8kViOQbGJhS00fI0YmNTYhlf7NtHQRPm4fF4cHLqQCSjyea5AFBRUU60raguLb9fxfc//gQp4QmAQFcIc1uy4g1a/ouFbXvw+AIiGRkpD5D4IImSRsxCanNNNs8FgJKS0vZEyrhohgO4FRlJLMPWpTPxjrWW/6LD48HGyYVYzrnzFyhowzyuhA6gyea5AFBQkE8UAqwpM4AH8WQNJDgcLuxcyTZotLwYOxfy9/b2bXInzwZIba7J5rnBIaG8R9nZwlfd8FJlNGAGoFAokJv9kEiGqbUNcQVbLS9GbGwCMeH+ygPCEx624NqRKG4Pj7KzhcEhoTyuVCr1lclkRMKcXcinZkwTdfce6mRSIhkWtkQrKS3NwMzGjuj+wtwcSKVk/89sgPShK5PJIJVKfbkSiYQog8fC0pI4f54NJCWTt48ytyUqpqylGZi3I3uPGxrqkfVQfTsFNWFsbAxzCwsiGRKJxJ8rlUq1G4AA5PVkdePERiYQ6RNWEtLySowtrKHDIzsDJ53xsgVS25NKpe25crmcKGrF2ZmogTBrqCMsHKlnqH5NUNURLpdL3H1ZqjEOgGzpLZfLjYgdgJEadv99HvWEMwCpRHOSTdhOLeF7rSkzAFLba3IARPNW0hLabKFeTlY1plaDss3YjKy2hrjIp1wDyoQD5LYnl8sNuHJ5HVHqGnEFXZbQoYMj0f3yOhnq1bwFlTpQU1VJLMOlA1kYLVsgtT25vE6PWYuJxwAAIABJREFUWyerIzq41pQZgJ9vP+J8huqKMkraaHkR1RWlRPcL9fTh4KAZpzWktlcnq9PnyupkIjIlNGMGYGJsBLER2ZoqNz2FkjZaXkQe4XtsZaPeXauehtT2ZHUyEVcmlRFFAYoNNGMGAAA2hOf4+ZmpGtGGiq1UlZWgoqSISIaTs/oHrTVBansyqUzIlcqkRClWmrIHAAAdO3Uiur+hvh55mdpZgLJ4lPqAWIanpycFTdgBqe1JZVIBV1pbSxRVoSlLAACYPXvWq1/0Ch6lkH9ItfyXhvp65GemEsngcLmYP28uJY2Yh9T2pLW1fG6tVMp79UtfpoTmLAGGDBwIEwtLIhnV5aUoLcijpJGWJnLTk4iXVy6d3WCnIZWrAHLbq5VKedyamhqi5HVNWgIAwJDXRhDLSP47UlsZmCLyOhnSYv8mljN5ylQK2rAHUturqanR4UqqJURnX5o0AwCAlcuXEcuoKitFDoX1qpbHpMfeg5wweo8v0MXSRQspacQOSG1PUi3hkHVb0EA83Nxg50geKJIacxfyOs0IOWUSSWU5spMTiOV49eqtMY1raMLVF+sTzVWrq6tp6cIaRo8dRyxDLpMiLYZ82trWSbp7m0qnpdmz51DQhl2Q2p5YLFZw9fT0iILgqwi76LCRlcuXEfekB4BHKQkoyc+hoFHb5FFKIkryHhHL0TcwxPSpUyhoxC5IbU9PT6+BKxIKiTIrNHEGYG1lBd8Bg4jlKBQKxEZchVSiee+RsqkoLkTSXTr1+6ZMnwUuV/NWu6S2p6evV88VikREZyvVGpoFt3fPHvAImy8AgFwmQ3T4ZTQ2ak6PemVTJ6395z0jn/qLjYyx+d3/UdCKfZDankgolHOFukKiFDZNXAIAgGP79nh97EQqsipLi/Hgr1tUZGk6CkUjYm5egYywP0MTy1a8CZGIKNqdtZDanq5QKOPqCnWJKiRWV2nu9Hb3zu3UGn3mpCUhI/4+FVmaikLRiLhbN1BWmE9FnpmVNYIC11KRxUZIbU8oFEq5ugLdWiIlNHQJAACmJiaYPmceNXmp0VFUAlo0EUVjI2JuXkN+Vho1mSGhb2nk2r8JUtvT1dWt4Qp0BRIyJTR3BgAAW979H9Vef+mxfyM1+i9q8jSBxsZGRN+8gsLsDGoyHTq44I25mnf09zSktqerqyvh8vkCosWWpu4BNCEUCrFyNd1pZEZ8NJLv/UlVprqiaGxEdNglFD3KoiaTw+FgywcfUpPHVkhtTyDQlXD5fD6RFE2fAQBA4JrV6NabqIHyf8hKjEVOmmY0qiQhNToKxbl06/SPnRyAkSOGU5XJRkhtTyAQVHL5fH4FiZCK8nIiJdSFY0ePwsjUjKrMpLuRkFQSvf1qTWl+DjITY6jKtHdyxoH9mtEG/FWQ2p5AIKggdgBpafQ2bdiMibExvjr4LXR0iLKn/0VDfT1iI65SOe9WN+QyKeIib1CVqSsU4cjRY9ChEMWpDpDankAgKOcKhUKixVdqatupgDN44ADMX7qCqsyqshKk3r9DVaY6EH87jNpZfxPvfbAVHV00o1FNcyC1PZFIlMnV19cPIxFSVFiIykryUs3qwtYt78Oje0+qMrMexFFfB7OZ7OQEFOWQdWJ+lsEjRmHh/DeoymQzlZWVKCosJJJhbmFxgysUCm/p6uoSCUpLJSvVpG4cPXIUhsamVGXG3w5DnZQoJEMtqC4vpX4CYmVrjx+++5aqTLZDanMGBgZYuerNv7jbt22tt7O3J4oGbEvLAACwtDDHydNnoG9I1FXtX9RJaxF/m2gyxnoUCgVib11HYwO9vAgzK2tcuHARQsKHmLpBanP29g61ZsaGjVwAsLKyLiZSJqVtOQAA8PLsijO/nYWYohMozs1GaUEuNXlsIy8jBdXlZI09nsbM0hpXrlyDna3m1PpvLqQ2Z2VtVQQAXAAwMzPVbgS2Ai/Prjjz+zmIKXYGTr0fRU0Wm2hsbER67D1q8swsrXD5ytU2afwAuc2ZmpplAP84ACMj4zgiZdrgDKAJz64e+O0sPSdQUVJINSqOLeSkPSDu6tuEqYUVLl+5Bns7zanw21JIbc7ExCQW+McBiMViol2ZtLT0Nl0Ft6uHO3479wdxa7EmUmOiNOr9bGyoR0YcnUxIUwtLXL5ytU0bv0KhQFpaOpEMY2PjSOAfB6Cnr3+ZRJhUWoucnLZd+qqruxuOnzoNHR55EZHq8jKqWXFMk52cSOXMn8fj4+y583Cwt6OglfqSk5MDKcGJEYfDgYND+yvAPw5g984d2ebm5kThaElJ2jLYPbt1w+vjJlCRlRZzFwoNiBCsl8uRkUDn6T915my4tqFAnxdBams27do1rFq5vAD4xwEAQDtbW6IF2u1b2oo3ALB39y7oGRgSy6mtrsKjNPV3qg+T4ohr+gOAgZExdm7fSkEj9YfU1mzb2T6J3HviACwsLIj6Wd0MDye5XWMwNjLC4uUrqcjKiLuPxgaimq2MIq+TISsxloqsd959D7q6mlnaq6WQ2pq5hcWTs+YnDsDExDSZROi9e39DIiGqLaIxbNwQBGs7B2I5stoaZCcnUtCIGbISY1AvJyo5CQBw6dgZb8ybR66QBiCRSHDvHllVKVNT0yd56E8cgJm5+W8kQuvr6xEZqV0GAACXy8V7Wz4Ah0PUdQ0AkJFwn7gpJhPUyaR4mBRPRdann35GRY4mEBl5C/X1ZLNCa2vrU01fP3EAhoaGP+mLxURnT+Fhmh3K2hImjRuLrj37EMuRy2R4+IDONFqVZMZHo4HwgwoAA4YMQ+8+vSlopBmQ2piJiYli5KjRh5u+f+IAdmzbKnVzcyMKCb4ZrnUAT7N370fg8QXEcrIexKpVn0FZbQ2yU8j7+fF4fHzy8ccUNNIcSG3M3cOjYPBA/ydTyn+VTG3fvv1tEuGxMTGoqGi7FW6exdPdHcPHjCeWUy+XIy3mLgWNVENaTBSVhJ+pM2bB1rbtBvw8S0VFBWJjyCooOTo6/Wud/i8HYGFpdYhEeGNjI25F3CQRoXHs3b2TStbgo5REVBST5X+rgrLCPOSkEe0nA3jcz2/bVs0v7NkSbkXcJK4eZWdn99PT3//LARiIxceMjY21+wAUMTE2xsJl5FWEFAoFEu6Eszo4qLGxEYl3IqjIWrVmHfT06DRl0RRIbcvS0rJx/Ybg00//7F8OYPu2rfVd3Ny18QCU2Ri0nsqxYHV5GfUimjTJjL8PSSV5kVgrWzsErl1DQSPNgtS23N09csyMDf/1BPlP2xR7e3siF56YmIDcXM3NaW8NOjo6+N97m6kcC6bH3kNNFfv2WSSVFciIj6Yi68MPt1F5rzSJ3NxcJCaSbaw6OLb/zxTiPw7AwtLyR5JBFAoFfj16lESERjJl4ni4d+9FLKexsQEJf7JvlpV4J5xKB2T3bj0wbuwYChppFr8ePUqcIdq+veP3z/6M8zyhFpaWDcVFRa1uqtapU2dE3NZ2vnmWezFxeH3oQCqBPZ16esOhkwcFrch5mBSHpLtEB0gAAA6Xi8tXb8DLy5OCVppFP+++RElAdnb2DdnZD/9T0/65Rt6lSxeiErVJSQ9w/x696i+aQndPDwwdOZaKrJT7UaxYCtRUVSCFUhWjIcNHao3/Ody/d484A7CLW5fM5/38uQ7Azs6eeCv/yGGiE0WNZe/uXdATk2cLNjbUIy+D+WrMeRmpVBKWBLpCfLL3IwoaaR40bMneweH6837+XAdgZWW1h7S7yonjv0KuhjHsysbczBRzFy2hIquylChwk1U6zJw3H5aWllRkaRJyuRwnjv9KJIMvEMDZ2WX38373XAfw0Z7d97t17070P1tSUoIrly+RiNBY3tkYAot25FVtNMUBGJmY4oP336OgjeZx5fIllJSUEMno06dvwcaQ4Oemlb5wo8/Nzf0I0ajQLgNeBJ/Hw6Z33iWWw4ZGIjR0CArZCIGAPGdCE6FhQx4eHr+86HcvdABWVlbvkmYHXjh/HuVtpHtwS5kZMAVdvHowrQbj2Ds5Y+niRUyrwUrKy8tx4fx5IhkmJiYKdw+P91/0+xc6gJ07thf36d2HqDJlXV0dTp44TiJCo9m9ezd0ePS6DasjO3buZFoF1nLyxHHU1ZEVVPH29klatWL5C5/CLz3rd3F1JW60fuiXn0lFaCx9evaAUE+faTUYZdiQIUyrwFpo2E4XN7dPX/b7lzoAExOTj21sbIjOeP6+e1ebIahFSwu5FXETf98lSwF3dHSs37jp7f0ve81LHcD2bVsbe/bqTdy8fvcu7TRPi5aWQMNmevXuE2FmbPjSfbxXhvs6OjltJlXkxvXruBulmT3vtGihzd2oKNy4fp1IBofDQefOXV55tvpKB/Dpx3vPd3FzIy73u2f3LlIRWrS0CWjYile37lWb33/32qte16yEH09Pr7OkCl28cB7x8XSqxGrRoqnEx8fj4gWyoz8A8PLyOtOc1zXLAbSztd3EJyxuqVAo8NFu7V6AFi0v46PdO4nTfoVCITp27LipOa9tlgPYs2tnSn9//6RXv/LlnDl9uk23Etei5WWkpqTgzOnTr37hKxg8ZEj8xtCQzOa8ttk5/+4eHstIE4QaGxux96M9RDI0iZKyckhrWt81l8cn70RMCqkOGZkZlDRRf/Z+tIe46CdfIECPnr2anW3WbAfwyd6PrvXr55fZKq2e4tdjR/Hw4UNSMRrB7TtRaKhvfcakQMh80UxSHW5GRFLSRL15+PAhfj1GXklr0KDBqZvfe7fZZf1aVPWnq6fnGtJabfX19di9cweRDE3hRtgNovsFQhElTZjT4eZNbZAYAOzeuYO45ZeOjg569+nTos60zy0J9jJ8fPvl3o68ZdOim54dlMPB+YuX0bMXeY08daWishLdvLxQVV7aahmW9k7w6s9sKG10+BUUZrd+Gi/Q1cXd+zGwsbamqJV6cTcqCiNeG0q8+Tdk6NDsy5cutaj8dIvr/nXv3j24pfc8i0KhQND6dcTrHXUmKPQtIuMHALGRMSVtmNOhTibD8hXkfRPUlcbGRgStX0ds/FwuF94+vutafF9Lb9j3+Wc/9uzZi6xCAYCY6Gh8+81BUjFqyab3NuPUEfJED1Nr5ttm0dAh/OplLFy8hNgI1JFvvzmImGjycur9/f0Ltrz/XotLB7Wq8m/PXr2olG/5cMtmFBcV0RClFpSUlmLWvPn4Yu8u4t55OjwejM2ZL6FlbG4JHR75acSpY4cxYuRIZGZmkiulJhQXFeHDLcSR9gCAfn79N7bmvhbvATTh6eVVERsTQ1zdMmDadHy+/wtSMawlr6AQFy9fxuHDh3H/zm1qXX7N29mj+8DhVGSRcu/6BRTnEhWSfgKHw0End08ETJuGEcNfg6uLCxW5bGTFsqVUKv706+dXevNmuFlr7m11NYo+ffrujI2JIXZfRw4fwuw5c+Dt40sqinHyCgpx5dp13I6MRFxcLLIz01FZVqqUqa21ozN1ma3FxsmFmgNQKBR4EBeN9zZF471NodAViWDX3hFubh7w9vbGkMGD4OLMnr+9tdyOvEWtZJ7/gAEvrPjzKlo9AwCAzp27SJKSHhAfRru5uePqjTDw1Kg6Tl5BIa5eu47IyEjEx8fiYUYGKstKVLKOFerpw29cADicVvduoYpCoUDEmaOolVSpZDyhSO+xU/DoCp++fTFkyGB0cHJSydg0qK+vx+AB/khIIM+N6dO3b+Wft2+3uv00kcX1HzDgnaSkB8SpSwkJ8fjqwJdYtpydu8H5hUW4cvUaIm/fRnxcLB5mpKvM2J+HQ2cP1hg/8Hja7tDZA0l3VRPUI62tQeqDBKQ+SMCZXx/XrhXp6cOuvRPcPTzQ19sbwwYPhqNje5Xo01K+OvAlFePX0dHBa8NHBJHIIJoBAMCAAQMzwsJuOBIJASAWi3Hrz7/Qrl07UlFE5BcW4cq1a7h9+zbiYmPxMDMdlaXMGfuz8AW68Bs3jRVhwE/TUF+P8NOHIZdJmVblCSJ9fdg7doC7++Plw7Ahg+HgQN6lmYTc3Fz49u2N6upqYlljxo5LPnP6VCcSGcRz7h69eo37+++/71dXVxGFCFZXV2Pxgvk4/ftZkOYcNJeCwiJcuf54Gh8XG4vszAxUlJZAoWBvfEKnXj6sM37g8alE516+iI24yrQqT6iVSJAcH4vk+FicPHoIwQD0xGLYt3eCe1dPePfti2FDh8DejrxHQ3NoaGjA4gXzqRi/hYWlYtDgwcRdVIlnAAAQMG36saNHDk8mFgRgzdp12PTO/2iI+hf/b+yPp/FZGU1PdvYa+7NYOTjB04/dRTRjI64iPyudaTVahJ7YAA5OHeDu4QEfbx8MHTIYdrb0Yyy2vP8etWS4JUuX/fDF/n1zSeVQcQAbQkIFp0+dLE9OSiIOTudwODhy7DgGE1SLldTU4OwfF3AzIgJxMTF4mJnO+if7qxAIRfAdNRl8XV2mVXkpcpkMt87+yoqmJSToGxjC3tEJHl094efXD+PHjoVYv/UVnK9euYKAKZOoLCV79OxZffHyVWMzY0PifuxUHAAALFu+Yv6XX+w/SEOembk5boTfhLV181IOGhoacD08AqfPnMGfkbfwMD2V2nk7G+Dx+egx6HUYsSDwpzlUFBfi72t/UGmDzha4XB3YOTqhj7cPxo0dg9eGDm32UjU/Pw8D+vuhpJi8jRqfz8eGkNDpW95/7zCxMFB0AAAwdNhrCVcuX+pCQ5ZvPz+cPH3mpW9y2K1IfPrZZ/jzZhhqqippDMs6dHg8dB84AiaW6pUsU1aYj3vXz6OBMMONrfB1ddHX1w9vrnoTgwcNfOHrGhoaMGHcWGql8cdPmBh98sTxblSEoZWhwC/Cy8trjImJKZV59q2Im9i5fdt/fl5eUYHNW7fDq0cPTBr9Oq798bvGGj9XRwfd/IepnfEDgImlNbr5DwNXRRu6qkYuk+HmtSuYOnEcunTpgrfe+R9KSv6b3LVz+zZqxm9jY9M4cNCg0VSE/QPVGQAAzJw1++AvP/80n4YsLpeLX0+cgv+AASgpK0Pwxrfwx+mTkNW2voqOuiASG8LTbzAMTc2ZVoWIytJixEZc1Vgn/TQ6PB6GvT4au3ftgJWlFcJu3MDkieOpZb0uXbb88/37Pm9Rvv+roO4AgkNCuefOnS2Li40lzhMAAHMLCwwdORZnjh9FTbVqIs2Yxrp9B3Tp05+Vx32toV4ux4O/IpCXmcq0KiqBL9DFyLHjEHH9GoqL6SS79enbt/zc+Yumr2r00VKoOwAAWLnqzYkHvvzyuFxO1tiwrSESG8C5a0/YOGlmAkx+VhrSYu62idkATfT09LBmXeDoDza/T1ye/1mU4gAAIGDa9KNHjxyeohThGoZIbAAn925o5+QKDpc9Ib7KQKFQID8zDelx91BTVcG0OmrBgoWLfvz6qwNzlCFbaQ4AAHz7+eVE3opgNraXpQiEIljY2sO8nQMsbB003vCfRaFQoCTvEYoeZaEoNxuyGuLmUxrJsNeGZ128cN5RWfKVmn7n4+vr+zArKzUn55H6pPkpCZHYAIam5jA0NYepta3ab+6RwuFwYN7OHubt7NEFjzcLS/NzUFlajMrSYtS2kf2el+Hs4lI3ctQoH2WOodQZAAAsW7Fy7sGvDnwn16CgkFch0jeAgak5jMzMYfCP0fMF7I7gYxvyOtljZ1BS/I9TKIJUQh5Dry7o6elh7brAqVs2v39MmeMo3QEAwJSpAad+PXZ0nNIHYgChvvifJ7vFkyc828N11RW5TPbEGTTNFDTVKSxavOTQgS+/mKHscVTiAADA28cn78/bt9UvouUp/t/YzZ8ydiHTarVp5DLpE2fweMZQBKma7ye8Nnx49oXz51WSt6yytXm/fn6+D7OykvPy8tRiP0Cop//YyM0eP9kNTM0h0Bo76+DrCmFmYwczm/9P6a2TSVFZ8v+zhKrSYrVxCi4urvLRY8Z6q2o8lc0AAGDpsuWLDn791QHSDii0EerpP1mrN11s6LqjhR510tp/zxRKi1l38iASibB+Q/DM99/93y+qGlOlDgAAJk+Z+vvxX4+NUumgT6Hb9GTXGnub519O4Z8ZA5Nh5kuWLjvyxf5901Q5psodQHBIKDc8PDxbVfEBegZGsLR3hImltdbYtbwSWW0NKkuLUV6Yj4LsTNRWqyZqceSo0Rk//PSzM+1Q31ehcgcAAEHBIcaXLlzIiI6+r5TeVmIjE1jaO8LKwQliY1NlDKGljVBVVorC7AwUZGdAUlGulDH8+vsXr16z1nHyxPEqX5MwsiG3c/u28sDGRs+ampqklJRk6o9kc1sHOHv2pC1WSxvEwMQUBiamaGxsVIoD6Na9u2TW7NkeTBg/QLkeQEvYvXNH9qgxY7zNLS2Jyxo9S2ZCNBL+DGNNJV8t6otCoUDCn2HITCDv3/csVtY29TNmzuqxZNHCAurCmwmjAegf7d4VM33a9OECJQTO5KQlIzr8MnEPPi1tl8aGBkSHX0ZOWjJ12TweDytXrhoQFLiOvvAWwMgewLNMm7tgw7Gfv9+uDGM1trCGV/8h2s0/LS2iTlqL6PArKC/Kpy6bw+Vi0pSAVccO//IZdeEt1YUNDgAAJs2YvffkkV9WKyhVT3kagVCErr6DYGqtTUzU8mpK83MRe+uaUiobc7hczFuw+O1vDuzfQl14K2CNAwCACVOn/Xj616OzlKETh8OBk3s3dOjaAxwOUQ8TLRqKQqFAeuzfyIi/r5T9Iw6XiwWLluz96ot9a6kLbyWscgAAMHr8xPNnT59UWt9rE0sbdO03CLoi4p6mWjQIWW0NYiOuoawwT2ljzJ43//AP3x6crrQBWgHrHAAADB85OvLiH2eVFg8t0BXCw3fgv+LHtbRdSvIeIe7WddQpsa9hwIyZFw///JPSHmythZUOAADGjJ/0x9kzJ0coUz8ndy84e/bSLgnaKAqFAmkxUciIp3/E1wSXy8XMOfNY9+RvgrUOAAAmBkz/7vTxY3OVeZRnbGGNrv0GQajX+rZPWtQPaY0EsRHXlLLL3wSPz8e8+QtZteZ/FlY7AACYOmvuzpNHD6+vV2KFYR6fjw5de8Khkxs4nLZVm6+toVA04mFSAtJj7yq1dZmuSIT5C5eE7vvko/92t2ERrHcAwOM4gVPHDm9XdqaW2NgUnXv5qmUnHi2vpqwwHw+ibqG6/L8dfGgiNjRSzFu4eMGnu3d8q9SBKKAWDgAApr+x+I3fjh89KKmqUPqC3cbJFR2799EGD2kIddJaJN+7g7yMFKWPZWZp3Th/4aIROz54/5LSB6OA2jgAAJi7dMWg308cv1xaVKD0eTpPIICLZy/YuXbRbhKqKQqFAo9SEpEaE4X6OuU3qWnfwVm+dMlSz5AN6x8ofTBKqJUDAIBVawM7nD51KjE7M12givEMTc3RubcvjMzUozW3lsdUlBTiwV+3UFlK3pK7OXh271G1fNkyVyYTe1qD2jkA4HE9gXPnz2ckxEQrpZ7A87B17ggnj+4Q6RuoakgtraBWUoWMuHtKSeB5EQMGD8lduWJlR6ZSeklQSwcAPK4sdC8m9ublC3/4KCN/4HlwOFzYODnD0a0b9A2NVDKmluYhqaxAZsJ95GWkQaFQzeeBx+djxqw5v+3Zs2ecqiv50EJtHUATs99YuOHk8WPbJFWVKluoczgcWNo7oYNHN23FIYapLi9Fetx9FGZnqLT+g7mVdeOCBQvnb/tg8/cqG1QJqL0DAIClq9Z0unjh/L2MlCSVb9tb2DrAyaObdo9AxVSUFCIj7j6Kch6qfGyvXn0qFsyb67ZqxfJclQ9OGY1wAE34DRkee+v6ZQ9VLQmextTaFh08usHE0kblY7clygrzkB53H6X5OSofW4fHx6jxE8NOHzs8QOWDKwmNcgAAMGHGnJ0Xfz+9nqke9EZmFmjXoSOs2nfQ9gOkhLxOhoKsdOSmJ6OipIgRHUzMLRVTps9Y/eUnH33KiAJKQuMcAADMXbqq+81rVyLSkxMZi+Thcrkwt22Pdk4uMG9n3+baf5OiaGxEcW42cjNSUZyThUYGZnVN9OjjXTpj+vSugWveVPsp/7NopANoYtjIMTevXvijX2Mjs3UB+bpCWLd3RrsOrm2+LfirqCwtRm56CvKz0iBXYnpuc+DzBZgUEHDq0I8/TGBUESWi0Q4AABYsWTb7wvk/DuY8zOIzrQsA6BsZo52TK6wdXbQZiP8grZEgPzMVuRkpSqu931I6dnGvnTRx4tQPt7z/O9O6KBONdwDA45iBmMSkc9cunh8uU0Kdt9YiNjaFqZUNTK3awcTSBjyBSoIbGae+rg5lhXkoLchFaUGe0pNzWoLY0EgxbsLEnz/eu3eOup7tt4Q24QCaWLRyddfIiIgb8ffvmjCty7NwOBwYmJjB5IlDsIYOjxWTFmIa6uUoK8xHaUEuygryUFVWwrqeDRwuF95+A3ImT5zgs271qmym9VEVbcoBNDFxxpzt1y9dCCorLmRtlg+Hw4WhmfkTZ6BvaAyhvphptZqFVFINSWX5E6OvLClWWXRea7Cxb98wZvyEwC8/+ehjpnVRNW3SAQDA9MUrjTJTk+9E3bzRUZnFRmjC1eFBz8AQ+oZG0Dc0hp6hEfQNjKBnaAQeX7XLh3p5HWoqKyCpqnj8b2U5JJUVqKmqRGMDu9q/vwihnj6GjBh1c84b8wdPHT1cedVBWEybdQBNzF60bOidyIgTyfGxap3lIxCKoG9oBD0DI/AEAvD4AvB4fOjw+eDx+dDh8R//7KmvdfiPlxgNcjnq5XVoqJej/pmvG+Ry1Nc//ll9XR1qqiogqaxQSs18VcHhcNCnX//CcWPGDArdsD6BaX0YRaFQaC+FAgGz5n5oaWMrB6DQXpp7uXbuIt0Q+tYKpj9vbLkYV4Bt1+QZsz6xsXfQOgINuzp2ca9dHRgUwvTni20X4wqw9Zo0Y/aowZD9AAADCElEQVQOW8cOdUx/cLVX6y8Oh6Po6N61ZsXawHVMf57YejGuANuviTPmbLHv4Cpj+sOsvVpo+B6eksUr16xk+vPD9qvNbwI2lwkz570T+3dUcHpSgp72PWMnXK4O3Lv1KO/r47vuq8/2fsu0PuqA1gG0kLmLlk2593fUvvjoe+bKbFiipfnweHz07OuT4+/vN3vHhx9cY1ofdULrAFrJmsD1HRMeJB+Mvv+3T2Fujg7T+rRF7B07yHv17n25V/duyzeGhmQyrY86onUAFJizcOmsxMSEDxNi7tvXVFcxrY5GY2hsqvDs0SO9V8+eaz/ase03pvVRd7QOgCLTl6ziS8pK9ifGx03LTHmgr8zWU20Jga4QLl3cKz28un27b++etW0hSUdVaB2Akpi1ZKVLUV7Ovgfxcf7ZGWm62ve5ZXB1dODcsXONu6fXeTd3j+UfvB2qVvX21QWtA1ABq9cG9khJz/gwIyPdOys91ahWonbl41WCgZGxooNrxzJXV9fr3T27btoYEpzItE6ajtYBMMDsBUvmZ2dnr8jKTHfLeZgplMtkTKvECEKRHuydnGucnJ2jO3fqtOfjndt+ZVqntobWATDM5AUrDOprqt7Oz8udkJud5ZCX/VCgLtmJLUVXKIKto5PUvr1jqo2t/VFLe8edH78bwmzdrzaO1gGwjA0hocL8ouLlebl5UwqLipyLiwqNS4uL+NIa9Vo26BsYwdzSos7C0qrU0tIq2al9+58nThj/7eCB/tqdURahdQBqwsIVqzuVVZTPLSst619aWuJaXlpiXFFaKqgqL+U0MBSQxBMIYGRi1mhsalZnbGpaZmpmnmJubn7dwd7+u23vbspgRCktLULrANSc4JBQXqWkZkB5efmIaklNx1qp1FwqlRpLZVJDmVQmksmkQqlUxpfXyXTq5XJOvbyOUy+XQ15Xx5H/s9TgC3TB5/MVfIEueAKBgs/nN/L5gkZdoVAuFIlqhUJhjUgoqhSJhKV6IlGhoaFBcjtr67Pr1q27bWZsyN5SP1peyf8BhSu7XAJjbgEAAAAASUVORK5CYII=");
		}

		result = imageString.toString();
		return result;
	}
}
