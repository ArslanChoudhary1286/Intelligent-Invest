package com.myapp.intellvest;


import com.google.firebase.database.Exclude;

public class UserModel {
    String name, number, email, password, address;
    private String mKey;
    private float earning;
    private int bonus;
    private int totalEarning;
    private float commission;
    private String userWithDrawlsAddress;
    private float userWithDrawlsAmount;
    private float pp0,pp1,pp2,pp3,pp4,pp5,pp6,pp7,pp8,pp9,pp10,pp11,pp12,pp13,pp14,pp15,pp16,pp17,pp18,pp19,pp20,pp21,pp22,pp23,pp24,pp25,pp26,pp27,pp28,pp29;
    private boolean p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29;
    private float sp0,sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9,sp10,sp11,sp12,sp13,sp14,sp15,sp16,sp17,sp18,sp19,sp20,sp21,sp22,sp23,sp24,sp25,sp26,sp27,sp28,sp29;
    private String referenceCode;


    public UserModel() {
    }

    public UserModel(String name, String number, String email, String password, String address, float earning, int bonus, int totalEarning, float commission, String userWithDrawlsAddress, float userWithDrawlsAmount, float pp0, float pp1, float pp2, float pp3, float pp4, float pp5, float pp6, float pp7, float pp8, float pp9, float pp10, float pp11, float pp12, float pp13, float pp14, float pp15, float pp16, float pp17, float pp18, float pp19, float pp20, float pp21, float pp22, float pp23, float pp24, float pp25, float pp26, float pp27, float pp28, float pp29, boolean p0, boolean p1, boolean p2, boolean p3, boolean p4, boolean p5, boolean p6, boolean p7, boolean p8, boolean p9, boolean p10, boolean p11, boolean p12, boolean p13, boolean p14, boolean p15, boolean p16, boolean p17, boolean p18, boolean p19, boolean p20, boolean p21, boolean p22, boolean p23, boolean p24, boolean p25, boolean p26, boolean p27, boolean p28, boolean p29, float sp0, float sp1, float sp2, float sp3, float sp4, float sp5, float sp6, float sp7, float sp8, float sp9, float sp10, float sp11, float sp12, float sp13, float sp14, float sp15, float sp16, float sp17, float sp18, float sp19, float sp20, float sp21, float sp22, float sp23, float sp24, float sp25, float sp26, float sp27, float sp28, float sp29, String referenceCode) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
        this.address = address;
        this.earning = earning;
        this.bonus = bonus;
        this.totalEarning = totalEarning;
        this.commission = commission;
        this.userWithDrawlsAddress = userWithDrawlsAddress;
        this.userWithDrawlsAmount = userWithDrawlsAmount;
        this.pp0 = pp0;
        this.pp1 = pp1;
        this.pp2 = pp2;
        this.pp3 = pp3;
        this.pp4 = pp4;
        this.pp5 = pp5;
        this.pp6 = pp6;
        this.pp7 = pp7;
        this.pp8 = pp8;
        this.pp9 = pp9;
        this.pp10 = pp10;
        this.pp11 = pp11;
        this.pp12 = pp12;
        this.pp13 = pp13;
        this.pp14 = pp14;
        this.pp15 = pp15;
        this.pp16 = pp16;
        this.pp17 = pp17;
        this.pp18 = pp18;
        this.pp19 = pp19;
        this.pp20 = pp20;
        this.pp21 = pp21;
        this.pp22 = pp22;
        this.pp23 = pp23;
        this.pp24 = pp24;
        this.pp25 = pp25;
        this.pp26 = pp26;
        this.pp27 = pp27;
        this.pp28 = pp28;
        this.pp29 = pp29;
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
        this.p9 = p9;
        this.p10 = p10;
        this.p11 = p11;
        this.p12 = p12;
        this.p13 = p13;
        this.p14 = p14;
        this.p15 = p15;
        this.p16 = p16;
        this.p17 = p17;
        this.p18 = p18;
        this.p19 = p19;
        this.p20 = p20;
        this.p21 = p21;
        this.p22 = p22;
        this.p23 = p23;
        this.p24 = p24;
        this.p25 = p25;
        this.p26 = p26;
        this.p27 = p27;
        this.p28 = p28;
        this.p29 = p29;
        this.sp0 = sp0;
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.sp3 = sp3;
        this.sp4 = sp4;
        this.sp5 = sp5;
        this.sp6 = sp6;
        this.sp7 = sp7;
        this.sp8 = sp8;
        this.sp9 = sp9;
        this.sp10 = sp10;
        this.sp11 = sp11;
        this.sp12 = sp12;
        this.sp13 = sp13;
        this.sp14 = sp14;
        this.sp15 = sp15;
        this.sp16 = sp16;
        this.sp17 = sp17;
        this.sp18 = sp18;
        this.sp19 = sp19;
        this.sp20 = sp20;
        this.sp21 = sp21;
        this.sp22 = sp22;
        this.sp23 = sp23;
        this.sp24 = sp24;
        this.sp25 = sp25;
        this.sp26 = sp26;
        this.sp27 = sp27;
        this.sp28 = sp28;
        this.sp29 = sp29;
        this.referenceCode = referenceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getEarning() {
        return earning;
    }

    public void setEarning(float earning) {
        this.earning = earning;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(int totalEarning) {
        this.totalEarning = totalEarning;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public String getUserWithDrawlsAddress() {
        return userWithDrawlsAddress;
    }

    public void setUserWithDrawlsAddress(String userWithDrawlsAddress) {
        this.userWithDrawlsAddress = userWithDrawlsAddress;
    }

    public float getUserWithDrawlsAmount() {
        return userWithDrawlsAmount;
    }

    public void setUserWithDrawlsAmount(float userWithDrawlsAmount) {
        this.userWithDrawlsAmount = userWithDrawlsAmount;
    }

    public float getPp0() {
        return pp0;
    }

    public void setPp0(float pp0) {
        this.pp0 = pp0;
    }

    public float getPp1() {
        return pp1;
    }

    public void setPp1(float pp1) {
        this.pp1 = pp1;
    }

    public float getPp2() {
        return pp2;
    }

    public void setPp2(float pp2) {
        this.pp2 = pp2;
    }

    public float getPp3() {
        return pp3;
    }

    public void setPp3(float pp3) {
        this.pp3 = pp3;
    }

    public float getPp4() {
        return pp4;
    }

    public void setPp4(float pp4) {
        this.pp4 = pp4;
    }

    public float getPp5() {
        return pp5;
    }

    public void setPp5(float pp5) {
        this.pp5 = pp5;
    }

    public float getPp6() {
        return pp6;
    }

    public void setPp6(float pp6) {
        this.pp6 = pp6;
    }

    public float getPp7() {
        return pp7;
    }

    public void setPp7(float pp7) {
        this.pp7 = pp7;
    }

    public float getPp8() {
        return pp8;
    }

    public void setPp8(float pp8) {
        this.pp8 = pp8;
    }

    public float getPp9() {
        return pp9;
    }

    public void setPp9(float pp9) {
        this.pp9 = pp9;
    }

    public float getPp10() {
        return pp10;
    }

    public void setPp10(float pp10) {
        this.pp10 = pp10;
    }

    public float getPp11() {
        return pp11;
    }

    public void setPp11(float pp11) {
        this.pp11 = pp11;
    }

    public float getPp12() {
        return pp12;
    }

    public void setPp12(float pp12) {
        this.pp12 = pp12;
    }

    public float getPp13() {
        return pp13;
    }

    public void setPp13(float pp13) {
        this.pp13 = pp13;
    }

    public float getPp14() {
        return pp14;
    }

    public void setPp14(float pp14) {
        this.pp14 = pp14;
    }

    public float getPp15() {
        return pp15;
    }

    public void setPp15(float pp15) {
        this.pp15 = pp15;
    }

    public float getPp16() {
        return pp16;
    }

    public void setPp16(float pp16) {
        this.pp16 = pp16;
    }

    public float getPp17() {
        return pp17;
    }

    public void setPp17(float pp17) {
        this.pp17 = pp17;
    }

    public float getPp18() {
        return pp18;
    }

    public void setPp18(float pp18) {
        this.pp18 = pp18;
    }

    public float getPp19() {
        return pp19;
    }

    public void setPp19(float pp19) {
        this.pp19 = pp19;
    }

    public float getPp20() {
        return pp20;
    }

    public void setPp20(float pp20) {
        this.pp20 = pp20;
    }

    public float getPp21() {
        return pp21;
    }

    public void setPp21(float pp21) {
        this.pp21 = pp21;
    }

    public float getPp22() {
        return pp22;
    }

    public void setPp22(float pp22) {
        this.pp22 = pp22;
    }

    public float getPp23() {
        return pp23;
    }

    public void setPp23(float pp23) {
        this.pp23 = pp23;
    }

    public float getPp24() {
        return pp24;
    }

    public void setPp24(float pp24) {
        this.pp24 = pp24;
    }

    public float getPp25() {
        return pp25;
    }

    public void setPp25(float pp25) {
        this.pp25 = pp25;
    }

    public float getPp26() {
        return pp26;
    }

    public void setPp26(float pp26) {
        this.pp26 = pp26;
    }

    public float getPp27() {
        return pp27;
    }

    public void setPp27(float pp27) {
        this.pp27 = pp27;
    }

    public float getPp28() {
        return pp28;
    }

    public void setPp28(float pp28) {
        this.pp28 = pp28;
    }

    public float getPp29() {
        return pp29;
    }

    public void setPp29(float pp29) {
        this.pp29 = pp29;
    }

    public boolean isP0() {
        return p0;
    }

    public void setP0(boolean p0) {
        this.p0 = p0;
    }

    public boolean isP1() {
        return p1;
    }

    public void setP1(boolean p1) {
        this.p1 = p1;
    }

    public boolean isP2() {
        return p2;
    }

    public void setP2(boolean p2) {
        this.p2 = p2;
    }

    public boolean isP3() {
        return p3;
    }

    public void setP3(boolean p3) {
        this.p3 = p3;
    }

    public boolean isP4() {
        return p4;
    }

    public void setP4(boolean p4) {
        this.p4 = p4;
    }

    public boolean isP5() {
        return p5;
    }

    public void setP5(boolean p5) {
        this.p5 = p5;
    }

    public boolean isP6() {
        return p6;
    }

    public void setP6(boolean p6) {
        this.p6 = p6;
    }

    public boolean isP7() {
        return p7;
    }

    public void setP7(boolean p7) {
        this.p7 = p7;
    }

    public boolean isP8() {
        return p8;
    }

    public void setP8(boolean p8) {
        this.p8 = p8;
    }

    public boolean isP9() {
        return p9;
    }

    public void setP9(boolean p9) {
        this.p9 = p9;
    }

    public boolean isP10() {
        return p10;
    }

    public void setP10(boolean p10) {
        this.p10 = p10;
    }

    public boolean isP11() {
        return p11;
    }

    public void setP11(boolean p11) {
        this.p11 = p11;
    }

    public boolean isP12() {
        return p12;
    }

    public void setP12(boolean p12) {
        this.p12 = p12;
    }

    public boolean isP13() {
        return p13;
    }

    public void setP13(boolean p13) {
        this.p13 = p13;
    }

    public boolean isP14() {
        return p14;
    }

    public void setP14(boolean p14) {
        this.p14 = p14;
    }

    public boolean isP15() {
        return p15;
    }

    public void setP15(boolean p15) {
        this.p15 = p15;
    }

    public boolean isP16() {
        return p16;
    }

    public void setP16(boolean p16) {
        this.p16 = p16;
    }

    public boolean isP17() {
        return p17;
    }

    public void setP17(boolean p17) {
        this.p17 = p17;
    }

    public boolean isP18() {
        return p18;
    }

    public void setP18(boolean p18) {
        this.p18 = p18;
    }

    public boolean isP19() {
        return p19;
    }

    public void setP19(boolean p19) {
        this.p19 = p19;
    }

    public boolean isP20() {
        return p20;
    }

    public void setP20(boolean p20) {
        this.p20 = p20;
    }

    public boolean isP21() {
        return p21;
    }

    public void setP21(boolean p21) {
        this.p21 = p21;
    }

    public boolean isP22() {
        return p22;
    }

    public void setP22(boolean p22) {
        this.p22 = p22;
    }

    public boolean isP23() {
        return p23;
    }

    public void setP23(boolean p23) {
        this.p23 = p23;
    }

    public boolean isP24() {
        return p24;
    }

    public void setP24(boolean p24) {
        this.p24 = p24;
    }

    public boolean isP25() {
        return p25;
    }

    public void setP25(boolean p25) {
        this.p25 = p25;
    }

    public boolean isP26() {
        return p26;
    }

    public void setP26(boolean p26) {
        this.p26 = p26;
    }

    public boolean isP27() {
        return p27;
    }

    public void setP27(boolean p27) {
        this.p27 = p27;
    }

    public boolean isP28() {
        return p28;
    }

    public void setP28(boolean p28) {
        this.p28 = p28;
    }

    public boolean isP29() {
        return p29;
    }

    public void setP29(boolean p29) {
        this.p29 = p29;
    }

    public float getSp0() {
        return sp0;
    }

    public void setSp0(float sp0) {
        this.sp0 = sp0;
    }

    public float getSp1() {
        return sp1;
    }

    public void setSp1(float sp1) {
        this.sp1 = sp1;
    }

    public float getSp2() {
        return sp2;
    }

    public void setSp2(float sp2) {
        this.sp2 = sp2;
    }

    public float getSp3() {
        return sp3;
    }

    public void setSp3(float sp3) {
        this.sp3 = sp3;
    }

    public float getSp4() {
        return sp4;
    }

    public void setSp4(float sp4) {
        this.sp4 = sp4;
    }

    public float getSp5() {
        return sp5;
    }

    public void setSp5(float sp5) {
        this.sp5 = sp5;
    }

    public float getSp6() {
        return sp6;
    }

    public void setSp6(float sp6) {
        this.sp6 = sp6;
    }

    public float getSp7() {
        return sp7;
    }

    public void setSp7(float sp7) {
        this.sp7 = sp7;
    }

    public float getSp8() {
        return sp8;
    }

    public void setSp8(float sp8) {
        this.sp8 = sp8;
    }

    public float getSp9() {
        return sp9;
    }

    public void setSp9(float sp9) {
        this.sp9 = sp9;
    }

    public float getSp10() {
        return sp10;
    }

    public void setSp10(float sp10) {
        this.sp10 = sp10;
    }

    public float getSp11() {
        return sp11;
    }

    public void setSp11(float sp11) {
        this.sp11 = sp11;
    }

    public float getSp12() {
        return sp12;
    }

    public void setSp12(float sp12) {
        this.sp12 = sp12;
    }

    public float getSp13() {
        return sp13;
    }

    public void setSp13(float sp13) {
        this.sp13 = sp13;
    }

    public float getSp14() {
        return sp14;
    }

    public void setSp14(float sp14) {
        this.sp14 = sp14;
    }

    public float getSp15() {
        return sp15;
    }

    public void setSp15(float sp15) {
        this.sp15 = sp15;
    }

    public float getSp16() {
        return sp16;
    }

    public void setSp16(float sp16) {
        this.sp16 = sp16;
    }

    public float getSp17() {
        return sp17;
    }

    public void setSp17(float sp17) {
        this.sp17 = sp17;
    }

    public float getSp18() {
        return sp18;
    }

    public void setSp18(float sp18) {
        this.sp18 = sp18;
    }

    public float getSp19() {
        return sp19;
    }

    public void setSp19(float sp19) {
        this.sp19 = sp19;
    }

    public float getSp20() {
        return sp20;
    }

    public void setSp20(float sp20) {
        this.sp20 = sp20;
    }

    public float getSp21() {
        return sp21;
    }

    public void setSp21(float sp21) {
        this.sp21 = sp21;
    }

    public float getSp22() {
        return sp22;
    }

    public void setSp22(float sp22) {
        this.sp22 = sp22;
    }

    public float getSp23() {
        return sp23;
    }

    public void setSp23(float sp23) {
        this.sp23 = sp23;
    }

    public float getSp24() {
        return sp24;
    }

    public void setSp24(float sp24) {
        this.sp24 = sp24;
    }

    public float getSp25() {
        return sp25;
    }

    public void setSp25(float sp25) {
        this.sp25 = sp25;
    }

    public float getSp26() {
        return sp26;
    }

    public void setSp26(float sp26) {
        this.sp26 = sp26;
    }

    public float getSp27() {
        return sp27;
    }

    public void setSp27(float sp27) {
        this.sp27 = sp27;
    }

    public float getSp28() {
        return sp28;
    }

    public void setSp28(float sp28) {
        this.sp28 = sp28;
    }

    public float getSp29() {
        return sp29;
    }

    public void setSp29(float sp29) {
        this.sp29 = sp29;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

}