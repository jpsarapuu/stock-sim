package com.example.stocksim.dataloader;

import com.example.stocksim.repositories.StockRepository;
import com.example.stocksim.repositories.TransactionRepository;
import com.example.stocksim.repositories.UserRepository;
import com.example.stocksim.services.BackgroundFeedService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private StockRepository stockRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private BackgroundFeedService backgroundFeedService;

    // Initializes BackgroundFeedServices for price updates every 3 minutes.
    // Contains sample data for development

    @Override
    public void run(String... args) throws Exception {

//        Stock arcoVara = Stock.builder().name("Arco Vara").ticker("ARC1T").build();
//        arcoVara.setDescription("Arco Vara Kinnisvarabüroo OÜ is a franchise company operating from the third quarter of 2018 under an exclusive licence agreement. Arco Vara’s trademark started in 1992, being the first real estate agency in Estonia.\n" +
//                "The main area of activity of Arco Vara Kinnisvarabüroo (Real Estate Agency) is real estate appraisal, brokerage, purchase, sale and rental related services for residential and commercial premises. We believe everyone deserves a home!");
//
//        Stock baltika = Stock.builder().name("Baltika").ticker("BLT1T").build();
//        baltika.setDescription("Baltika Group, headquartered in Tallinn and founded in 1928, is the leading fashion brand house in the Baltics. We own, operate and  develop five internationally successful fashion brands: Monton, Mosaic, Baltman, Bastion and Ivo Nikkolo.\n" +
//                "Throughout 90 years of experience, we have developed from a manufacturer to a brand house, and in the process we have become a leading fashion design enterprise in the Baltic region.");
//
//        Stock ekspressG = Stock.builder().name("Ekspress Grupp").ticker("EEG1T").build();
//        ekspressG.setDescription("AS Ekspress Grupp is a leading media group in the Baltic States, whose activities include publishing, printing services and online media content production.\n" +
//                "The company’s goal is to be a truly modern media company with a strong foothold in all markets where actively present. Our mission is to offer new and exciting experiences both on paper and in digital media, without compromising the quality of the news, selection of the topics and journalistic objectivity.");
//
//        Stock eften = Stock.builder().name("EfTEN Real Estate Fund III").ticker("EFT1T").build();
//        eften.setDescription("EfTEN Capital is an independent Baltic commercial real estate fund manager. EfTEN holds an alternative investment fund manager (AIF) license issued by Estonian Financial Supervision Authority. EfTEN currently manages four funds.\n" +
//                "EfTEN Real Estate Fund III AS (founded in 2015) is a public closed real estate fund, targeted mainly for retail investors. The funds investment strategy is value added and opportunistic. Funds investment region is Baltic States.");
//
//        Stock harjuElekter = Stock.builder().name("Harju Elekter").ticker("HAE1T").build();
//        harjuElekter.setDescription("Harju Elekter has been manufacturing electrical equipment since 1968. The operation of the Harju Elekter Group is be divided into two business segments – firstly production and secondly real estate and all other activities.\n" +
//                "The Parent company AS Harju Elekter include the coordination of the co-operation within the Group, management of subsidiaries and related companies through their supervisory and management boards, management of finances and investments of the Group and management of development and expansion activities.");
//
//        Stock lhvG = Stock.builder().name("LHV Group").ticker("LHV1T").build();
//        lhvG.setDescription("LHV was founded in 1999, by Rain Lõhmus and Andres Viisemann. We have over 430 people working for us. Our offices are located in Tallinn and Tartu. More than 200,000 clients are using our banking services. LHV’s leading pension funds have nearly 177,000 clients.\n" +
//                "Our mission is to help to create Estonian capital. According to our vision, the people and enterprises of Estonia have the courage to think big, start things and invest in the future. Our values are to be simple, supportive and effective.");
//
//        Stock merkoEhitus = Stock.builder().name("Merko Ehitus").ticker("MRK1T").build();
//        merkoEhitus.setDescription("Long-term experience in various countries, a wide scope of construction services, flexibility, reliability and meeting the deadlines and primarily quality have helped group companies to achieve the market leader position in Estonia.\n" +
//                "Depending on the requirements of the contracting entities, the group companies perform both small-scale construction works as well as large scale, complicated and innovative projects, with a focus on general contracting and project management.");
//
//        Stock nordecon = Stock.builder().name("Nordecon").ticker("NCN1T").build();
//        nordecon.setDescription("For many years, the cornerstone of the action strategy of the group has been focusing on general contracting and project management, which balances its work portfolio between buildings and civil engineering works.\n" +
//                "Supporting activities such as road maintenance and concrete work, which provide added value and efficiency and help manage risks, have been progressively added to the selection of services in order to support the principal activity of the company.");
//
//        Stock prFoods = Stock.builder().name("PRFoods").ticker("PRF1T").build();
//        prFoods.setDescription("AS PRFoods’ key market is Finland, where the company is amongst the three largest fish production companies. Since the acquisition of John Ross Jr. and Coln Valley Smokery in the summer of 2017, the Group has expanded its sales to 37 countries in Europe, North and South America, and Asia.\n" +
//                "Main activity of the Group is fish manufacturing in four contemporary production buildings in Renko and Kokkola (Finland), in Saaremaa (Estonia), and in Aberdeen (Great Britain). ");
//
//        Stock proKapitalG = Stock.builder().name("Pro Kapital Grupp").ticker("PKG1T").build();
//        proKapitalG.setDescription("Pro Kapital Grupp is one of the leading Estonian real estate developers focusing on contemporary large-scale commercial and residential property developments in the capitals of Estonia, Latvia and Lithuania.\n" +
//                "Since its establishment in 1994, Pro Kapital has completed more than 20 projects with the total surface area for sale extending over 250,000 square meters. Pro Kapital projects have been among the most remarkable developments in the Baltic real estate sector.");
//
//        Stock skanoG = Stock.builder().name("Skano Group").ticker("SKN1T").build();
//        skanoG.setDescription("Skano Group AS is one of the largest timber processing companies in Estonia with more than 50 years of experience in timber refinement. The company is 100% privately owned and the first and currently only Estonian timber processing company with shares listed on the NASDAQ OMX Stock Exchange.\n" +
//                "The structural units of Skano Group AS include Skano Furniture engaged in furniture manufacturing and retail and Skano Fibreboard which produces natural wooden fibreboards.");
//
//        Stock tallinkG = Stock.builder().name("Tallink Grupp").ticker("TAL1T").build();
//        tallinkG.setDescription("We are the leading provider of high-quality mini-cruise and passenger transport services in the northern Baltic Sea region, as well as the leading provider of ro-ro cargo services on selected routes.\n" +
//                "The company's vision is to be the market pioneer in Europe by offering excellence in leisure and business travel and sea transportation services. Our customer value proposal is to offer an enjoyable travel experience that exceeds customer expectations and makes them want to return.");
//
//        Stock talKaubamajaG = Stock.builder().name("Tallinna Kaubamaja Grupp").ticker("TKM1T").build();
//        talKaubamajaG.setDescription("Operators that form Tallinna Kaubamaja Group mostly pursue their business in the sphere of retail and wholesale trade. The Group companies contribute more than one tenth of retail trade in Estonia in general and employ more than 3,500 people.\n" +
//                "The Group owns well-known brands like Kaubamaja, Selver, Selveri Köök, Tartu Kaubamaja Centre, Viking Motors, KIA, ABC King, SHU, I.L.U., Viking Security. The loyalty programme of the Group, Partner Card, extends to 585,000 loyal customers and is the largest in Estonia.");
//
//        Stock talVesi = Stock.builder().name("Tallinna Vesi").ticker("TVEAT").build();
//        talVesi.setDescription("We are responsible for treating and supplying drinking water to our customers and for leading off and treating wastewater in Tallinn.\n" +
//                "Every day, we give approximately 70 million litres of drinking water to the water network, and collect and direct 120 million litres of wastewater to our wastewater treatment plant where it will be treated and, after turning into environmentally safe effluent, discharged to the sea.");
//
//        Stock trigonPD = Stock.builder().name("Trigon Property Development").ticker("TPD1T").build();
//        trigonPD.setDescription("AS Trigon Property Development is a property development company investing principally into Baltic property. AS Trigon Property Development has evolved as a result of the division of AS Viisnurk on September 19, 2007 and the core business activity is property development in Central and Eastern Europe.\n" +
//                "Currently, AS Trigon Property Development owns one property development project, which is a 32.8 ha land-area in Niidu area, Pärnu city, Estonia.");
//
//        stockRepository.save(arcoVara);
//        stockRepository.save(baltika);
//        stockRepository.save(ekspressG);
//        stockRepository.save(eften);
//        stockRepository.save(harjuElekter);
//        stockRepository.save(lhvG);
//        stockRepository.save(merkoEhitus);
//        stockRepository.save(nordecon);
//        stockRepository.save(prFoods);
//        stockRepository.save(proKapitalG);
//        stockRepository.save(skanoG);
//        stockRepository.save(tallinkG);
//        stockRepository.save(talKaubamajaG);
//        stockRepository.save(talVesi);
//        stockRepository.save(trigonPD);

//        User user1 = User.builder().username("user").passwordHash(passwordEncoder.encode("pass")).build();

//        Transaction transaction1 = Transaction.builder().amount(2L).price(1.21).build();
//        user1.setBalance(user1.getBalance() - transaction1.getAmount() * transaction1.getPrice());
//        transaction1.setStock(arcoVara);
//        transaction1.setUser(user1);
//
//        Transaction transaction2 = Transaction.builder().amount(3L).price(0.04).build();
//        user1.setBalance(user1.getBalance() - transaction2.getAmount() * transaction2.getPrice());
//        transaction2.setStock(baltika);
//        transaction2.setUser(user1);
//
//        Transaction transaction3 = Transaction.builder().amount(-1L).price(1.41).build();
//        user1.setBalance(user1.getBalance() - transaction3.getAmount() * transaction3.getPrice());
//        transaction3.setStock(arcoVara);
//        transaction3.setUser(user1);
//
//        user1.setTransactions(Arrays.asList(transaction1, transaction2, transaction3));
//        userRepository.save(user1);

//        transactionRepository.save(transaction1);
//        transactionRepository.save(transaction2);
//        transactionRepository.save(transaction3);

        backgroundFeedService.startProcess();
    }
}
