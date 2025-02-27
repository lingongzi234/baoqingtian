<template>
    <div class=" flex flex-row gap-8 w-screen-xl">
        <div class="flex gap-4 flex-col items-start basis-[%50]">
            <h2>Payment</h2>
            <hr class=" text-black  font-800 w-2xl">
            <div class="flex flex-col gap-4 items-start">
                <h4>Pay With:</h4>
                <div class=" flex flex-row justify-around">
                    <div class="flex flex-row justify-center items-center">
                        <input type="checkbox" />
                        <div>Card</div>
                    </div>
                    <div class="flex flex-row justify-center items-center">
                        <input class=" rounded border border-red outline-nonepaymen" type="checkbox" />
                        <div>Card</div>
                    </div>
                    <div class="flex flex-row justify-center items-center">
                        <input type="checkbox" />
                        <div>Card</div>
                    </div>
                </div>
                <div class="flex flex-row gap-4 items-center">
                    <h4>Card Number</h4>
                    <input class="Input" />
                </div>
                <div class="flex flex-row gap-4 items-center">
                    <h4>Expiration Date</h4>
                    <input class="Input" />
                    <h4>CVV</h4>
                    <input class="Input" />
                </div>
                <input type="checkbox">
                <span></span>
            </div>
            <button class=" w-2xl bg-green">Pay USD</button>
        </div>
        <hr>
        <div class=" basis-[50%]">
            <h2>Order Summary</h2>
            <hr>
            <div>
                <img>
                <div>
                    <div>Taxmax Leather Bag</div>
                    <div>Brown Vegan Leather</div>
                </div>
            </div>
            <hr>
            <input class="Input">
            <hr>
            <div>
                <div>Subtotal</div>
                <div>Shipping</div>
            </div>
            <hr>
        </div>
    </div>
</template>
