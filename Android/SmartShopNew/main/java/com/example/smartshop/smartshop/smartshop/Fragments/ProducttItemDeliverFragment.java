package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import ua.smartshop.R;

public class ProducttItemDeliverFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_contact, container,
                false);

        WebView webDescription = (WebView) rootView.findViewById(R.id.contact_web_view);
        WebSettings settings = webDescription.getSettings();
        // включаем поддержку JavaScript
        settings.setJavaScriptEnabled (true);
        settings.setDefaultTextEncodingName("UTF-8");
        webDescription.loadDataWithBaseURL(null, "<div><span style=\"font-size: 1.2em;\">Внимание! Из соображений безопасности доставки негабаритных товаров осуществляются до ворот / парадной / проходной. </span><a class=\"inlineWide\" href=\"#security\" title=\"Доставка негабаритных товаров\"><b>Подробнее...</b></a>\n" +
                "<div style=\"display: none;\">\n" +
                "<div id=\"security\" class=\"htmlContent\">\n" +
                "<p>Уважаемые покупатели! Мы всегда стараемся максимально соответствовать Вашим требованиям и делать покупки максимально удобными для Вас. Однако целый ряд печальных инцидентов заставил нас с 15.02.2013 принять решение осуществлять доставки негабаритных товаров до ворот/парадной/проходной (телефоны, планшеты, ноутбуки и т.п.). Осмотр товара и передача денег производится в машине у курьера.</p>\n" +
                "<br />\n" +
                "<p><span style=\"text-decoration: underline;\">Часто задаваемые вопросы:</span></p>\n" +
                "<br />\n" +
                "<p>- Как же я нормально проверю ноутбук в машине? А если он не заряжается к примеру?<br /><i>Если при проверке дома Вы обнаружите брак - мы поменяем этот товар в течении 14 дней. Главное: сохранить в идеальном состоянии упаковку, комплектацию, все пленки-наклейки и, конечно, само устройство. Также обращаем внимание на то, что в холодное время года (ниже +5) техника должна отстояться 2 часа в теплом помещении перед включением.</i></p>\n" +
                "<br />\n" +
                "<p>- А крупную технику вы тоже доставляете только до парадной? (Холодильник, стиральную машину и т.п.)<br /><i>Нет. Крупную технику мы заносим непосредственно в квартиру.</i></p>\n" +
                "<br />\n" +
                "<p>- А можно самостоятельно к вам приехать за товаром? <br /><i>Да. Точка выдачи заказов находится по адресу ул.Пушкинская 64 (только после того как менеджер подтвердит, что товар уже там).</i></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<p><br /><br /></p>\n" +
                "<div id=\"vkl_dostavka\">\n" +
                "<p style=\"padding-left: 23px;\">Вы можете оформить заказ пятью способами:<br /> &nbsp;&nbsp;&nbsp;1) по телефону<br /> &nbsp;&nbsp;&nbsp;2) через ICQ<br /> &nbsp;&nbsp;&nbsp;3) с помощью \"Корзины\" на сайте<br /> &nbsp;&nbsp;&nbsp;4) через онлайн-чат<br /> &nbsp;&nbsp;&nbsp;5) через Skype</p>\n" +
                "<br />\n" +
                "<h3>Доставка по Одессе:</h3>\n" +
                "<ul>\n" +
                "<li>самовывоз с нашего магазина (<a href=\"gde-my-nahodimsya.html\" target=\"_blank\"><span style=\"text-decoration: underline;\">г.Одесса, ул.Пушкинская 64</span></a>) - бесплатно</li>\n" +
                "<li>Центр, Фонтан, Таирова, Черемушки, Молдованка, Слободка - бесплатно при заказе на сумму от 2000 грн, заказы на меньшую сумму - 35 грн</li>\n" +
                "<li>Ленпоселок, Черноморка, Совиньон - 50 грн</li>\n" +
                "<li>пос.Котовского - 80 грн (на след.день - 50 грн)</li>\n" +
                "<li>Ильичевск - 100 грн</li>\n" +
                "<li>Одесская область - 5 грн/км (в каждую сторону)</li>\n" +
                "</ul>\n" +
                "<p style=\"padding-left: 25px; margin-top: -16px;\">&nbsp;</p>\n" +
                "<div class=\"more-dost\">&nbsp;</div>\n" +
                "<br /><br />\n" +
                "<p>Имеется услуга <b>\"Срочная доставка\"</b>. Выполняется за 1-2 часа. Стоимость - договорная (зависит от загруженности службы доставки на данный момент, вида товара и Вашего месторасположения).</p>\n" +
                "<p style=\"padding: 20px 0 0 25px;\"><span style=\"text-decoration: underline;\">Подьем на этаж:</span></p>\n" +
                "<ul>\n" +
                "<li>телевизоры от 40\" - 10 грн/этаж (если помещается в лифт - оплачивается 1 этаж)</li>\n" +
                "<li>крупная бытовая техника - 20 грн/этаж <br /> (если помещается в лифт в упаковке - оплачивается 1 этаж)</li>\n" +
                "<li>холодильники 185 см и более, а также КБТ весом от 50 кг - 30 грн/этаж <br /> (если помещается в лифт в упаковке - оплачивается 1 этаж)</li>\n" +
                "<li>холодильники syde-by-side - 40 грн/этаж <br /> (если помещается в лифт в упаковке - оплачивается 1 этаж)</li>\n" +
                "</ul>\n" +
                "<p style=\"text-align: center;\"><a target=\"_blank\" href=\"http://maps.yandex.ru/?um=H1aYT8-k9ugD1c6aJzUwcRqTHBWW6QrB&amp;l=map\"><b>«SMART-Shop, зоны доставки по Одессе» на Яндекс.Картах</b></a></p>\n" +
                "<br /><br />\n" +
                "<h3><img src=\"https://cdn1.iconfinder.com/data/icons/finalflags/24/Ukraine-Flag.png\" /> Доставка по Украине:</h3>\n" +
                "<div style=\"padding-left: 23px;\">Доставка по Украине осуществляется через:<br /> - Новую Почту (<a href=\"http://novaposhta.ua\" target=\"_blank\" rel=\"nofollow\"><span style=\"text-decoration: underline;\">http://novaposhta.ua</span></a>), наложенным платежом (оплата после получения и осмотра товара)</div>\n" +
                "</div>", "text/html", "en_US", null);

        return rootView;
    }
}
